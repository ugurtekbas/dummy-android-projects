package com.example.newgithubuser

import android.app.Application
import com.example.newgithubuser.di.AppComponent
import com.example.newgithubuser.di.DaggerAppComponent

class NewGithubUserApplication : Application() {

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }

}
