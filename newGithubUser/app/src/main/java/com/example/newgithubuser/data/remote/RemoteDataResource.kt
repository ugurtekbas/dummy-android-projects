package com.example.newgithubuser.data.remote

import io.reactivex.Single
import javax.inject.Inject

private const val USER_NAME = "xing"
private const val ITEM_NR_PER_PAGE = 10

class RemoteDataResource @Inject constructor(
    private val githubApi: GithubApi
) : RemoteDataSource {

    override fun getUserRepos(page: Int): Single<List<RepoResponse>> =
        githubApi.getUserGithubReposWithPaging(USER_NAME, ITEM_NR_PER_PAGE, page)

}
