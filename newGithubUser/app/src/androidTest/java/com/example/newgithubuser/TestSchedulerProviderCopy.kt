package com.example.newgithubuser

import com.example.newgithubuser.util.SchedulerProvider
import io.reactivex.schedulers.TestScheduler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestSchedulerProviderCopy @Inject constructor() : SchedulerProvider {

    val testScheduler: TestScheduler = TestScheduler()

    override fun uiScheduler() = testScheduler
    override fun ioScheduler() = testScheduler
}
