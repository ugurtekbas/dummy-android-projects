package com.example.newgithubuser

import android.content.Context
import com.example.newgithubuser.di.ActivityScopeModule
import com.example.newgithubuser.di.AppComponent
import com.example.newgithubuser.di.NetworkModule
import com.example.newgithubuser.presentation.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class, NetworkModule::class])
interface TestAppComponent : AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TestAppComponent
    }

    fun inject(activity: MainActivityTest)
}
