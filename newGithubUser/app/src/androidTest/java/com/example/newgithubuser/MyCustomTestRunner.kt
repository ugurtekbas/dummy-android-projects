package com.example.newgithubuser

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class MyCustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestNewGithubUserApplication::class.java.name, context)
    }
}
