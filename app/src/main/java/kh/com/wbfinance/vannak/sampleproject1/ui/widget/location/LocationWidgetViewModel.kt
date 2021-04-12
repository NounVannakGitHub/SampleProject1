package kh.com.wbfinance.vannak.sampleproject1.ui.widget.location

import kh.com.wbfinance.vannak.sampleproject1.model.repo.Repository
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewModel
import kh.com.wbfinance.vannak.sampleproject1.util.rx.SchedulerProvider

data class LocationWidgetViewModel(
    private val appRepository: Repository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

}