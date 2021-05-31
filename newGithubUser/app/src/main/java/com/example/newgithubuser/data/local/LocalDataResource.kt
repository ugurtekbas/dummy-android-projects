package com.example.newgithubuser.data.local

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class LocalDataResource @Inject constructor(
    private val repoDoa: RepoDao
): LocalDataSource {
    override fun saveList(list: List<RepoEntity>) =
        Completable.fromCallable { repoDoa.saveAll(list) }

    override fun getAllRepos(): Single<List<RepoEntity>> = Single.just(repoDoa.getAll())

    override fun clearTable(): Completable =
        Completable.fromCallable{ repoDoa.clear() }

    override fun getThat(): Observable<List<RepoEntity>> = repoDoa.getThat()
}
