package com.example.newgithubuser.domain

import com.example.newgithubuser.data.DataRepository
import com.example.newgithubuser.data.local.LocalDataSource
import com.example.newgithubuser.data.remote.RemoteDataSource
import com.example.newgithubuser.di.ActivityScope
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
open class GetReposUseCase @Inject constructor(
    private val repository: DataRepository
) : UseCaseInterace {
    override fun getNextPage(page: Int) = repository.requestReposOnNextPage(page)
    override fun getCurrentRepos() = repository.getCachedRepos()
}

interface UseCaseInterace {
    fun getNextPage(page: Int): Completable
    fun getCurrentRepos(): Observable<List<RepoItem>>
}
