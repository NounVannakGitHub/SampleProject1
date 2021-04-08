package kh.com.wbfinance.vannak.sampleproject1.util.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
}