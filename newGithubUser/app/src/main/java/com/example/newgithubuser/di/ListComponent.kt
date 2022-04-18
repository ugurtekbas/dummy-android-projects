package com.example.newgithubuser.di

import com.example.newgithubuser.presentation.ui.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ListModule::class, ViewModelModule::class])
interface ListComponent {

    // This is for AppComponent to know how to create ListComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): ListComponent
    }

    fun inject(activity: MainActivity)
}
