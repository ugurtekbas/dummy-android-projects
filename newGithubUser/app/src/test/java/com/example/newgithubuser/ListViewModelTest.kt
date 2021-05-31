package com.example.newgithubuser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.newgithubuser.domain.GetReposUseCase
import com.example.newgithubuser.domain.RepoItem
import com.example.newgithubuser.presentation.ui.ListViewModel
import io.reactivex.Observable
import io.reactivex.Single
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ListViewModelTest {
    /*
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = ImmediateSchedulerRule()

    /*
    val rule: TestRule = ImmediateSchedulerRule()
     */

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

    private val getRepoUseCase: GetReposUseCase = (mock() {
        on { getCurrentRepos() } doReturn Observable.just(repoList)
        on { getNextPage(any()) } doReturn Single.just(repoList)
    })

    private val scheduleProvider = TestSchedulerProvider()
    private val observer: Observer<Response> = mock()
    private lateinit var viewModel: ListViewModel

    @Before
    fun setUp() {
        viewModel = ListViewModel(
            getRepoUseCase,
            scheduleProvider
        )
        viewModel.liveDataRepoList.observeForever(observer)
    }


    @Test
    fun testNull() {
        assertNotNull(viewModel.liveDataRepoList)

        assertTrue(viewModel.liveDataRepoList.hasObservers())
    }

    @Test
    fun `when live data value is changed should observe right response`() {
        viewModel.liveDataRepoList.value = Response.Success(repoList)

        verify(observer).onChanged(Response.Success(repoList))
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
        LiveDataTestUtil.getValue(viewModel.liveDataRepoList)

        /*
        viewModel.liveDataRepoList.observeForever(observer)
        val error = Throwable("Error happened")
        whenever(getRepoUseCase.getNextPage(any())).thenReturn(Single.error(error))
        viewModel.loadMore()
        getRepoUseCase.getNextPage(any()).test().assertError(error)
        //assertEquals(Response.Error(error), viewModel.liveDataRepoList.value)

        verify(observer).onChanged(Response.Error(error))
        */
    }
     */
}
