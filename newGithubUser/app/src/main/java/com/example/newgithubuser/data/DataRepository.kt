package com.example.newgithubuser.data

import com.example.newgithubuser.data.local.RepoEntity
import com.example.newgithubuser.data.remote.RepoResponse
import com.example.newgithubuser.domain.RepoItem
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface DataRepository {
    fun getCachedRepos(): Observable<List<RepoItem>>
    fun requestReposOnNextPage(page: Int): Completable
}
