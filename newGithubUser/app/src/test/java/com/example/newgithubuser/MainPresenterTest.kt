package com.example.newgithubuser

import com.example.newgithubuser.domain.GetReposUseCase
import com.example.newgithubuser.domain.RepoItem
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.junit.Test

class MainPresenterTest {
    /*
    private val firstItem = RepoItem(
        id = 1,
        name = "John",
        description = "Tester name",
        ownerLogin = "true",
        profilePicture = "profile-url",
        background = android.R.color.white,
        url = "url"
    )
    private val secondItem = RepoItem(
        id = 1,
        name = "Joe",
        description = "Second Tester name",
        ownerLogin = "true",
        profilePicture = "profile-url",
        background = android.R.color.holo_green_dark,
        url = "url"
    )

    private val repoList = listOf(firstItem, secondItem)
    private val databaseObservable: PublishSubject<List<RepoItem>> = PublishSubject.create()
    private val mockUsecase: GetReposUseCase = mock {
        on { getCurrentRepos() } doReturn Observable.just(repoList)
        on { getNextPage(1) } doReturn Completable.complete()
    }
    //private val mockView: MainPresenter.View = mock()
    private val testSchedulerProvider = TestSchedulerProvider()
    private val presenter = MainPresenter(
        mockUsecase,
        testSchedulerProvider
    ).apply {
        setView(mockView)
    }

    @Test fun `observe repo table query after`(){
        presenter.setObserve()
        //testSchedulerProvider.testScheduler.advanceTimeBy(5, TimeUnit.SECONDS)
        testSchedulerProvider.testScheduler.triggerActions()

        verify(mockView).showRepos(repoList)
    }

    /*@Test fun `set repos after load requestobserve repo table query after`(){
        whenever(mockUsecase.getThat()).thenReturn(Observable.just(mutableListOf<RepoItem>()))
        presenter.setObserve()

        testSchedulerProvider.testScheduler.triggerActions()
        whenever(mockUsecase.getThat()).thenReturn(Observable.just(repoList))
        presenter.onLoadRepoRequest()
        testSchedulerProvider.testScheduler.triggerActions()

        verify(mockView).showRepos(repoList)
    }*/

    @Test fun `don't set if list is empty`(){
        whenever(mockUsecase.getCurrentRepos()).thenReturn(Observable.just(mutableListOf<RepoItem>()))
        presenter.setObserve()
        testSchedulerProvider.testScheduler.triggerActions()

        verifyZeroInteractions(mockView)
    }

    @Test fun `onError`(){
        val error = Throwable("Load Error")
        whenever(mockUsecase.getNextPage(1)).thenReturn(Single.error(error))
        presenter.onLoadRepoRequest()
        testSchedulerProvider.testScheduler.triggerActions()

        verify(mockView).showError(error)
    }

    @Test fun `on long click`(){
        presenter.onItemLongClicked(firstItem)

        verify(mockView).showDialog(firstItem)
    }
     */
}
