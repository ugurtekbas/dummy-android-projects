package com.example.newgithubuser.util

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun uiScheduler() : Scheduler
    fun ioScheduler() : Scheduler
}
