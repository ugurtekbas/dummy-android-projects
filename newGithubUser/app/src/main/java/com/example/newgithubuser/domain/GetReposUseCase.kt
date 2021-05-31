package com.example.newgithubuser.domain

import com.example.newgithubuser.data.DataRepository
import com.example.newgithubuser.data.local.LocalDataSource
import com.example.newgithubuser.data.remote.RemoteDataSource
import com.example.newgithubuser.di.ActivityScope
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class GetReposUseCase @Inject constructor(
    private val repository: DataRepository
) {
    fun getNextPage(page: Int) = repository.requestReposOnNextPage(page)
    fun getCurrentRepos() = repository.getCachedRepos()
}
