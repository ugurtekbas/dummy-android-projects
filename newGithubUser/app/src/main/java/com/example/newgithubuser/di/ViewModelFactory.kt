package com.example.newgithubuser.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newgithubuser.domain.GetReposUseCase
import com.example.newgithubuser.presentation.ui.ListViewModel
import com.example.newgithubuser.util.SchedulerProvider
import com.example.newgithubuser.util.StringResourceProvider
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get() as T
    }

}

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
class ViewModelModule {

    @Provides
    fun providesViewModelFactory(
        providerMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
    ): ViewModelProvider.Factory {
        return ViewModelFactory(
            providerMap
        )
    }

    @Provides
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    fun provideListViewModel(
        getReposUseCase: GetReposUseCase,
        schedulerProvider: SchedulerProvider,
        stringProvider: StringResourceProvider
    ): ViewModel {
        return ListViewModel(
            getReposUseCase,
            schedulerProvider,
            stringProvider
        )
    }
}
