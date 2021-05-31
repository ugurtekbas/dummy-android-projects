package com.example.newgithubuser

import android.util.Log
import android.util.Log.w
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.newgithubuser.data.remote.RepoResponse
import com.example.newgithubuser.domain.GetReposUseCase
import com.example.newgithubuser.domain.RepoItem
import com.example.newgithubuser.presentation.ui.ListViewModel
import com.example.newgithubuser.presentation.ui.ViewState
import com.example.newgithubuser.util.StringResourceProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class ListViewModelNewTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = ImmediateSchedulerRule()

    private val repoList = listOf(
        RepoItem(
            id= 1,
            name = "name",
            description = "desc",
            ownerLogin = "owner",
            profilePicture = "profile",
            background = 0,
            url = "url"
        )
    )

    private val getRepoUseCase: GetReposUseCase = mock {
        on { getCurrentRepos() } doReturn Observable.just(repoList)
        on { getNextPage(any()) } doReturn Completable.complete()
    }

    private val observer: Observer<ViewState> = mock()
    private val stringProvider: StringResourceProvider = mock()
    private val scheduleProvider = TestSchedulerProvider()
    //private val viewModel = ListViewModel(getRepoUseCase, scheduleProvider, stringProvider)
    private lateinit var viewModel: ListViewModel

    @Before
    fun setup() {
        viewModel = ListViewModel(getRepoUseCase, scheduleProvider, stringProvider)
    }

    @Test
    fun `when view start observing, state should be loading`() {
        /*viewModel.viewState.observeForever {
            //assertNotNull(it)
            assertThat(it.shouldShowLoading).isTrue()
        }*/

        viewModel.viewState.observeForever(observer)
        //assertThat(viewModel.viewState.value).isEqualTo(ViewState())
        //assertTrue(viewModel.viewState.hasObservers())
        //verify(getRepoUseCase).getCurrentRepos()
        //verify(observer).onChanged(ViewState())
        var a = true;
        /*viewModel.viewState.getOrAwaitValue {
            a = false;
        }*/
        val b = LiveDataTestUtil.getValue(viewModel.viewState)
        //assertNotNull(b)
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    try {
        afterObserve.invoke()
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }
    } finally {
        this.removeObserver(observer)
    }
    @Suppress("UNCHECKED_CAST")
    return data as T
}
