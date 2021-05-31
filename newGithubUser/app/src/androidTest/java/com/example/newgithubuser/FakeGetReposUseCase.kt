package com.example.newgithubuser

import com.example.newgithubuser.domain.RepoItem
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import io.reactivex.SingleEmitter

class FakeGetReposUseCase {
    private val firstItem = RepoItem(
        id = 1,
        name = "John",
        description = "Tester name",
        ownerLogin = "true",
        profilePicture = "profile-url",
        background = android.R.color.white,
        url = "url"
    )
    private val secondItem = RepoItem(
        id = 1,
        name = "Joe",
        description = "Second Tester name",
        ownerLogin = "true",
        profilePicture = "profile-url",
        background = android.R.color.holo_green_dark,
        url = "url"
    )
    private val repoList = listOf(firstItem, secondItem)
    fun getThat() : Observable<List<RepoItem>> {
        return Observable.create<List<RepoItem>> { emitter: ObservableEmitter<List<RepoItem>> ->
            emitter.onNext(repoList)
        }
    }
}
