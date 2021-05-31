package com.example.newgithubuser.data

import com.example.newgithubuser.data.local.LocalDataSource
import com.example.newgithubuser.data.remote.RemoteDataSource
import com.example.newgithubuser.di.ActivityScope
import com.example.newgithubuser.domain.RepoItem
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RepoDataRepository @Inject constructor(
    private val remoteResource: RemoteDataSource,
    private val localResource: LocalDataSource,
    private val mapper: DataMapper,
): DataRepository {

    override fun getCachedRepos(): Observable<List<RepoItem>> =
        localResource.getThat().map { it.map { mapper.mapToDomainModel(it) } }

    override fun requestReposOnNextPage(page: Int): Completable =
        remoteResource.getUserRepos(page)
            .flatMapCompletable {
                localResource.saveList(
                    it.map{ mapper.mapToCacheModel(it)}
                )
            }

}
