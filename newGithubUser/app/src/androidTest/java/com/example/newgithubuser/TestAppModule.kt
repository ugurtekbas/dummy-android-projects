package com.example.newgithubuser

import androidx.lifecycle.ViewModel
import com.example.newgithubuser.di.ViewModelKey
import com.example.newgithubuser.domain.GetReposUseCase
import com.example.newgithubuser.presentation.ui.ListViewModel
import com.example.newgithubuser.util.SchedulerProvider
import com.example.newgithubuser.util.StringResourceProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
class TestAppModule {

    @Provides
    @Singleton
    fun provideUseCase() = FakeGetReposUseCase()

    @Provides
    @Singleton
    fun provideScheduler() = TestSchedulerProviderCopy()

    @Provides
    @Singleton
    fun provideListViewModel(
        usecase: FakeGetReposUseCase,
        schedulerProvider: TestSchedulerProviderCopy,
        stringProvider: StringResourceProvider
    ): ViewModel {
        return ListViewModel(
            usecase,
            schedulerProvider,
            //stringProvider
        )
    }
}
