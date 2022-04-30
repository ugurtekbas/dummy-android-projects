package com.example.newgithubuser

import com.example.newgithubuser.data.DataRepository
import com.example.newgithubuser.domain.GetReposUseCase
import com.example.newgithubuser.domain.RepoItem
import com.example.newgithubuser.domain.UseCaseInterace
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import io.reactivex.SingleEmitter
import javax.inject.Singleton

class FakeDataRepo : DataRepository {

    private val firstItem = RepoItem(
        id = 1,
        name = "Johnny Cash",
        description = "Tester name",
        ownerLogin = "true",
        profilePicture = "profile-url",
        background = android.R.color.white,
        url = "url"
    )
    private val secondItem = RepoItem(
        id = 2,
        name = "Joe Pelo",
        description = "Second Tester name",
        ownerLogin = "true",
        profilePicture = "profile-url",
        background = android.R.color.holo_green_dark,
        url = "url"
    )
    private val repoList = listOf(firstItem, secondItem)

    override fun getCachedRepos(): Observable<List<RepoItem>> {
        return Observable.create<List<RepoItem>> { emitter: ObservableEmitter<List<RepoItem>> ->
            emitter.onNext(repoList)
        }
    }

    override fun requestReposOnNextPage(page: Int): Completable = Completable.complete()
}

@Singleton
class FakeGetReposUseCase : UseCaseInterace {
    private val fakeRepository = FakeDataRepo()

    override fun getNextPage(page: Int) = fakeRepository.requestReposOnNextPage(1)
    override  fun getCurrentRepos() = fakeRepository.getCachedRepos()
}

@Singleton
class EmptyGetReposUseCase : UseCaseInterace {
    override fun getNextPage(page: Int) = Completable.complete()
    override fun getCurrentRepos() = Observable.just(emptyList<RepoItem>())
}
