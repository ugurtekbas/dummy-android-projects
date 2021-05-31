package com.example.newgithubuser.data.local

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface LocalDataSource {
    fun saveList(list: List<RepoEntity>): Completable
    fun getAllRepos(): Single<List<RepoEntity>>
    fun clearTable(): Completable
    fun getThat(): Observable<List<RepoEntity>>
}
