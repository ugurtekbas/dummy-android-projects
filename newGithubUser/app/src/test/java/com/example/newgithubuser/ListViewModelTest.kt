package com.example.newgithubuser

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.newgithubuser.domain.GetReposUseCase
import com.example.newgithubuser.domain.RepoItem
import com.example.newgithubuser.presentation.ui.ListViewModel
import com.example.newgithubuser.presentation.ui.ViewEvent
import com.example.newgithubuser.presentation.ui.ViewState
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import junit.framework.Assert.*
import okhttp3.Response
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.*
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class ListViewModelTest {

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

    private val getRepoUseCase: GetReposUseCase = (mock {
        on { getCurrentRepos() } doReturn Observable.just(repoList)
        on { getNextPage(any()) } doReturn Completable.complete()
    })

    private val scheduleProvider = TestSchedulerProvider()
    private val observer: Observer<ViewState> = mock()
    private val eventObserver: Observer<ViewEvent> = mock()
    private lateinit var viewModel: ListViewModel

    @Before
    fun setUp() {
        viewModel = ListViewModel(getRepoUseCase,scheduleProvider)
    }

    @Test
    fun `when ViewModel is initiated should get repolist (because of init function)`() {
        val expected = ViewState(shouldShowLoading = false, repoList = repoList)

        assertEquals(expected, viewModel.viewState.getOrAwaitValue())
    }

    @Test
    fun `should invoke usecase method at initialize, only once`() {
        verify(getRepoUseCase, times(1)).getCurrentRepos()
    }

    @Test
    fun `when load more is called should fetch data from server`() {
        viewModel.loadMore()
        viewModel.loadMore()
        viewModel.loadMore()

        verify(getRepoUseCase, times(1)).getNextPage(1)
        verify(getRepoUseCase, times(1)).getNextPage(2)
        verify(getRepoUseCase, times(1)).getNextPage(3)
    }

    @Test
    fun `when load more is failed should pass error response`() {
        whenever(getRepoUseCase.getNextPage(any())).thenReturn(Completable.error(Throwable()))
        val expected = ViewEvent.ShowErrorMessage("HEY")

        viewModel.loadMore()
        assertEquals(expected, viewModel.viewEvent.getOrAwaitValue())
        /*
        viewModel.liveDataRepoList.observeForever(observer)
        val error = Throwable("Error happened")

        viewModel.loadMore()
        getRepoUseCase.getNextPage(any()).test().assertError(error)
        //assertEquals(Response.Error(error), viewModel.liveDataRepoList.value)

        verify(observer).onChanged(Response.Error(error))
        */
    }

    @Test
    fun `a testDeney`() {
        val viewModel2 = ListViewModel(getRepoUseCase,scheduleProvider)
        viewModel2.deneyiDegistir("Hey hey")

        assertEquals(viewModel2.testDeney.getOrAwaitValue(), "Hey hey")
    }
}
