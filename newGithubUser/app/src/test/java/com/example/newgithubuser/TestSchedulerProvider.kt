package com.example.newgithubuser

import com.example.newgithubuser.util.SchedulerProvider
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestSchedulerProvider @Inject constructor() : SchedulerProvider {

    override fun uiScheduler() = Schedulers.trampoline()
    override fun ioScheduler() = Schedulers.trampoline()
}
