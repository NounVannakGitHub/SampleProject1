package kh.com.wbfinance.vannak.sampleproject1.ui.percountry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.data.helper.Constant
import kh.com.wbfinance.vannak.sampleproject1.data.mapper.CountryDataMapper
import kh.com.wbfinance.vannak.sampleproject1.model.dao.Country
import kh.com.wbfinance.vannak.sampleproject1.model.repo.Repository
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.TextItem
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewModel
import kh.com.wbfinance.vannak.sampleproject1.util.SingleLiveEvent
import kh.com.wbfinance.vannak.sampleproject1.util.ext.addTo
import kh.com.wbfinance.vannak.sampleproject1.util.rx.SchedulerProvider

class PerCountryActivityViewModel(
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

    fun loadData(country: String) {

        appRepository.specificCountry(country)
                .observeOn(schedulerProvider.io())
                .map {
                    with(it){
                        val list = mutableListOf<BaseViewItem>()

                        with (data){
                            var countries: List<Country>? = listOfNotNull(data)
                            list.add(CountryDataMapper.transformIntoCountryProvinceGraph(countries))
                        }

                        list.toList()
                    }
                }
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { _loading.postValue(true)}
                .doFinally { _loading.postValue(false) }
                .subscribe({
                    _liveItems.postValue(it)
                },{
                    it.printStackTrace()
                    _toastMessage.postValue(Constant.ERROR_MESSAGE)
                }).addTo(compositeDisposable)

    }

}