package com.example.newgithubuser

import com.example.newgithubuser.data.DataRepository
import com.example.newgithubuser.domain.GetReposUseCase
import com.example.newgithubuser.domain.RepoItem
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetReposUseCaseTest {

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
    private val page = 1
    private val mockedRepository: DataRepository = mock() {
        on { getCachedRepos() } doReturn Observable.just(repoList)
        on { requestReposOnNextPage(1) } doReturn Completable.complete()
    }
    private val useCase = GetReposUseCase(mockedRepository)

    @Test fun `get that test`() {
        useCase.getCurrentRepos().test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(repoList)

        verify(mockedRepository).getCachedRepos()
    }

    @Test fun `getNextPage that test`() {
        useCase.getNextPage(page).test()
            .assertNoErrors()
            .assertComplete()

        verify(mockedRepository).requestReposOnNextPage(page)
    }
}
