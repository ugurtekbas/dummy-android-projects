package com.example.newgithubuser

import com.example.newgithubuser.util.SchedulerProvider
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider : SchedulerProvider {

    val testScheduler: TestScheduler = TestScheduler()

    override fun uiScheduler() = testScheduler
    override fun ioScheduler() = testScheduler
}
