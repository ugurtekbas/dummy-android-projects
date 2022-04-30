package com.example.newgithubuser

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newgithubuser.di.ActivityScope
import com.example.newgithubuser.di.AppComponent
import com.example.newgithubuser.domain.UseCaseInterace
import com.example.newgithubuser.util.SchedulerProvider
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import org.mockito.Mockito
import javax.inject.Singleton

@Singleton
@Component(modules = [TestActivityScopeModule::class, TestNetworkModule::class])
interface TestAppComponent : AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TestAppComponent
    }
}

@Module(subcomponents = [TestListComponent::class])
class TestActivityScopeModule

@ActivityScope
@Subcomponent(modules = [TestListModule::class, TestViewModelModule::class])
interface TestListComponent {

    // This is for AppComponent to know how to create ListComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): TestListComponent
    }
}

@Module
class TestListModule {

    @ActivityScope
    @Provides
    fun provideRemoteUseCase(): UseCaseInterace =
        FakeGetReposUseCase()

    @ActivityScope
    @Provides
    fun provideSchedulerProvider() : SchedulerProvider = TestSchedulerProviderCopy()
}

@Module
class TestViewModelModule {
    @ActivityScope
    @Provides
    fun provideListViewModelFactory(): ViewModelProvider.Factory = MockViewModelFactory()

    class MockViewModelFactory (): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Mockito.mock(modelClass)
        }
    }
}
