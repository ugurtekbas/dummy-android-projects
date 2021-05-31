package com.example.newgithubuser.data.remote

import io.reactivex.Single

interface RemoteDataSource {
    fun getUserRepos(page: Int) : Single<List<RepoResponse>>
}
