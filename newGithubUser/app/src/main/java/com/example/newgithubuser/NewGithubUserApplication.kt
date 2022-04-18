package com.example.newgithubuser

import android.app.Application
import com.example.newgithubuser.di.AppComponent
import com.example.newgithubuser.di.DaggerAppComponent

open class NewGithubUserApplication : Application() {

    val appComponent : AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
