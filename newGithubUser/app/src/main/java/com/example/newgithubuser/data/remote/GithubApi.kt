package com.example.newgithubuser.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("orgs/{user}/repos")
    fun getUserGithubReposWithPaging(
        @Path("user") user: String,
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1
    ): Single<List<RepoResponse>>
}
