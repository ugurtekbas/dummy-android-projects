package com.example.newgithubuser

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestAppModule {

    @Provides
    @Singleton
    fun provideUseCase() = FakeGetReposUseCase()
}
