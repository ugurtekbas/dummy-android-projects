package com.example.newgithubuser

import com.example.newgithubuser.FakeDataRepo.Companion.JOES_REPO
import com.example.newgithubuser.data.remote.GithubApi
import com.example.newgithubuser.data.remote.OwnerResponse
import com.example.newgithubuser.data.remote.RepoResponse
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import io.reactivex.Single

private const val BASE_URL = "https://api.github.com/"

@Module
class TestNetworkModule {

    @Singleton
    @Provides
    fun provideGSon(): Gson = Gson()

    @Singleton
    @Provides
    fun provideGSonConverterFactory(gSon: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gSon)

    @Singleton
    @Provides
    fun provideService(converterFactory: GsonConverterFactory): GithubApi =
        TestGithubApiImp()
}

class TestGithubApiImp : GithubApi {
    // Actually this is not needed because FakeGetReposUseCase is already returning a result
    private val firstItem = RepoResponse(
        id = 1,
        name = "John",
        description = "Tester name",
        owner = OwnerResponse("Login", "avatar_url", "html_url"),
        url = "url",
        fork = true
    )
    private val secondItem = RepoResponse(
        id = 2,
        name = JOES_REPO,
        description = "Second Tester name",
        owner = OwnerResponse("Login", "avatar_url", "html_url"),
        url = "url",
        fork = false
    )

    private val repoList = listOf(firstItem, secondItem)

    override fun getUserGithubReposWithPaging(
        user: String,
        perPage: Int,
        page: Int
    ): Single<List<RepoResponse>> {
        return Single.just(listOf())
    }
}
