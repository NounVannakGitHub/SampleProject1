package kh.com.wbfinance.vannak.sampleproject1.ui.percountry.indonesia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.data.mapper.IndonesiaDailyDataMapper
import kh.com.wbfinance.vannak.sampleproject1.data.model.indonesia.IndonesiaDaily
import kh.com.wbfinance.vannak.sampleproject1.data.model.indonesia.IndonesiaPerProvince
import kh.com.wbfinance.vannak.sampleproject1.data.repository.Repository
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.TextItem
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewModel
import kh.com.wbfinance.vannak.sampleproject1.util.Constant
import kh.com.wbfinance.vannak.sampleproject1.util.SingleLiveEvent
import kh.com.wbfinance.vannak.sampleproject1.util.ext.addTo
import kh.com.wbfinance.vannak.sampleproject1.util.rx.SchedulerProvider

class CountryIndonesiaViewModel(
    private val appRepository: Repository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _toastMessage = SingleLiveEvent<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    private val _liveItems = MutableLiveData<List<BaseViewItem>>()
    val items: LiveData<List<BaseViewItem>>
        get() = _liveItems

    fun loadData() {
        Observable.zip(
            appRepository.getIndonesiaDaily(),
            appRepository.getIndonesiaPerProvince(),
            BiFunction<List<IndonesiaDaily>, List<IndonesiaPerProvince>, List<BaseViewItem>> { daily, province ->
                val list = mutableListOf<BaseViewItem>()
                if (province.isNullOrEmpty().not()) {
                    list.add(TextItem(R.string.case_per_province_chart))
                    list.add(IndonesiaDailyDataMapper.transformIntoCountryProvinceGraph(province))
                }
                if (daily.isNullOrEmpty().not()) {
                    list.add(TextItem(R.string.case_daily_chart))
                    list.add(IndonesiaDailyDataMapper.transformIntoCountryDailyGraph(daily))
                    list.add(TextItem(R.string.case_daily))
                    list.addAll(IndonesiaDailyDataMapper.transformToPerCountryDaily(daily.reversed()))
                }
                return@BiFunction list
            })
            .doOnSubscribe { _loading.postValue(true) }
            .doFinally {
                _loading.postValue(false)
            }
            .subscribe({
                _liveItems.postValue(it)
            }, {
                it.printStackTrace()
                _toastMessage.postValue(Constant.ERROR_MESSAGE)
            })
            .addTo(compositeDisposable)
    }
}