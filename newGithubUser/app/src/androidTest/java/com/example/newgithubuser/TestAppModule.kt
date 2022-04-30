package com.example.newgithubuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newgithubuser.di.ViewModelKey
import com.example.newgithubuser.domain.GetReposUseCase
import com.example.newgithubuser.domain.UseCaseInterace
import com.example.newgithubuser.presentation.ui.ListViewModel
import com.example.newgithubuser.util.SchedulerProvider
import com.example.newgithubuser.util.StringResourceProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import org.mockito.Mockito.mock
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Module
class TestAppModuleWithLoading {

    @Provides
    @Singleton
    fun provideUseCase(): UseCaseInterace = mock(UseCaseInterace::class.java)

    @Provides
    @Singleton
    fun provideScheduler() = TestSchedulerProviderCopy()

    /*@Provides
    @Singleton
    fun provideListViewModel(
        usecase: UseCaseInterace,
        schedulerProvider: TestSchedulerProviderCopy,
        stringProvider: StringResourceProvider
    ): ViewModel {
        return mock(ListViewModel::class.java)
    }*/

    @Provides
    @Singleton
    fun provideListViewModelFactory(): ViewModelProvider.Factory = MockViewModelFactory()

    class MockViewModelFactory (): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return mock(modelClass)
        }
    }
}

class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get() as T
    }

}

@Module
class TestAppModuleWithEmptyList () {

    @Provides
    @Singleton
    fun provideUseCase(): UseCaseInterace = EmptyGetReposUseCase()

    @Provides
    @Singleton
    fun provideScheduler() = TestSchedulerProviderCopy()
}

@Module
class BaseTestAppModule {
    @Provides
    @Singleton
    fun provideUseCase(): UseCaseInterace = FakeGetReposUseCase()

    @Provides
    @Singleton
    fun provideScheduler() = TestSchedulerProviderCopy()

    @Provides
    @Singleton
    fun provideListViewModel(
        usecase: UseCaseInterace,
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
