package kh.com.wbfinance.vannak.sampleproject1.ui.dailygraph.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kh.com.wbfinance.vannak.sampleproject1.data.mapper.CovidDailyDataMapper
import kh.com.wbfinance.vannak.sampleproject1.data.model.CovidDaily
import kh.com.wbfinance.vannak.sampleproject1.data.repository.Repository
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.DailyItem
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewModel
import kh.com.wbfinance.vannak.sampleproject1.util.SingleLiveEvent
import kh.com.wbfinance.vannak.sampleproject1.util.ext.addTo
import kh.com.wbfinance.vannak.sampleproject1.util.rx.SchedulerProvider

class DailyGraphViewModel(
    private val appRepository: Repository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {
    private val _toastMessage = SingleLiveEvent<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    private val _loading = SingleLiveEvent<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _dailyItems = MutableLiveData<List<CovidDaily>>()
    val dailyItems: LiveData<List<CovidDaily>>
        get() = _dailyItems
    val dailyItemsVH: LiveData<List<DailyItem>>
        get() = Transformations.map(_dailyItems) {
            CovidDailyDataMapper.transform(it)
        }

    fun loadCacheDailyData() {
        /*Assume daily data just got fresh data from remote api on previous page
          for UX Purpose, we directly load cache
        */
        _dailyItems.postValue(appRepository.getCacheDaily().orEmpty())
    }

    fun loadRemoteDailyData() {
        appRepository.daily().subscribe({ response ->
            _loading.postValue(false)
            response.data?.let {
                _dailyItems.postValue(it)
            }
        }, {
            _loading.postValue(false)

        }).addTo(compositeDisposable)
    }
}