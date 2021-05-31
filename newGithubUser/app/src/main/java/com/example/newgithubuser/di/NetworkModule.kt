package com.example.newgithubuser.di

import com.example.newgithubuser.data.remote.GithubApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

private const val BASE_URL = "https://api.github.com/"

@Module
class NetworkModule {

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
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GithubApi::class.java)
}
