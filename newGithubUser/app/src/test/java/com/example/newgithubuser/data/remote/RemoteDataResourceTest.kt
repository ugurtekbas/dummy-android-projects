package com.example.newgithubuser.data.remote

import io.reactivex.Single
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class RemoteDataResourceTest {

    private val response = RepoResponse (
        id = 1,
        name = "John",
        description = "Tester name",
        owner = OwnerResponse(login = "owner-login", avatarUrl = "avatar", url = "url"),
        fork = true,
        url = "url"
    )
    private val api: GithubApi = mock {
        on { getUserGithubReposWithPaging(any(), any(), any()) } doReturn Single.just(listOf(response))
    }
    private val dataResource = RemoteDataResource(api)
    private val page = 1
    private val userName = "xing"
    private val itemNrOfPages = 10

    @Test
    fun `when getting user repos should request with correct params`() {
        dataResource.getUserRepos(page)

        verify(api).getUserGithubReposWithPaging(userName, itemNrOfPages, page)
    }

    @Test
    fun `when getting user repos should succeed with correct response`() {
        dataResource.getUserRepos(page).test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(listOf(response))
    }
}
