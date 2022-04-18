package com.example.newgithubuser

import android.app.Application
import com.example.newgithubuser.di.AppComponent

class TestNewGithubUserApplication : NewGithubUserApplication() {

    override fun initializeComponent(): AppComponent {
        return DaggerTestAppComponent.factory().create(applicationContext)
    }
}
