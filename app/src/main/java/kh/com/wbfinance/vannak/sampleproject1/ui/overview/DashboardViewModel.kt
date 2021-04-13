package kh.com.wbfinance.vannak.sampleproject1.ui.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.functions.Function4
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.data.base.BaseResult
import kh.com.wbfinance.vannak.sampleproject1.data.helper.Constant
import kh.com.wbfinance.vannak.sampleproject1.data.mapper.AseanCountryDataMapper
import kh.com.wbfinance.vannak.sampleproject1.data.mapper.CovidOverviewDataMapper
import kh.com.wbfinance.vannak.sampleproject1.model.dao.Continent
import kh.com.wbfinance.vannak.sampleproject1.model.dao.Country
import kh.com.wbfinance.vannak.sampleproject1.model.dao.CovidOverview
import kh.com.wbfinance.vannak.sampleproject1.model.repo.Repository
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.ErrorStateItem
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.LoadingStateItem
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.TextItem
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewModel
import kh.com.wbfinance.vannak.sampleproject1.util.SingleLiveEvent
import kh.com.wbfinance.vannak.sampleproject1.util.ext.addTo
import kh.com.wbfinance.vannak.sampleproject1.util.rx.SchedulerProvider

class DashboardViewModel(
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

    private fun showLoadingState(){
        if(_liveItems.value?.isEmpty() == null ||
            _liveItems.value?.firstOrNull { it is ErrorStateItem } != null){
            _liveItems.value = listOf(LoadingStateItem())
        }
    }

    private fun showErrorState(throwable: Throwable){
        _loading.value = false
        if(_liveItems.value?.isEmpty() == null ||
            _liveItems.value?.firstOrNull { it is ErrorStateItem || it is LoadingStateItem } != null){
            _liveItems.value = listOf(handleThrowable(throwable))
        }
    }

    fun loadDashboard() {
        showLoadingState()

        val overviewObservable = appRepository.overview()
            .observeOn(schedulerProvider.io()) //all stream below will be manage on io thread

        val covidSpreadMetricByContinent = appRepository.allContinents()
            .observeOn(schedulerProvider.io())

        val aseanObservable = appRepository.aseanCountries()
            .observeOn(schedulerProvider.io())

        Observable.combineLatest(
            overviewObservable,
            covidSpreadMetricByContinent,
            aseanObservable,
            Function3<
                    BaseResult<CovidOverview>,
                    BaseResult<List<Continent>>,
                    BaseResult<List<Country>>,
                    Pair<List<BaseViewItem>, Throwable?>> {
                overview, continents, asean ->

                val items: MutableList<BaseViewItem> = mutableListOf()
                var currentThrowable: Throwable? = null

                with(overview){
                    items.add(CovidOverviewDataMapper.transform(data))
                    error?.let { currentThrowable = it }
                }

                /*
                * All Continents
                * */

                /*
                * Favorite Country
                * */

                /*
                * Asean Zone Covid-19 data
                * */
                items.add(TextItem(R.string.asean_zone))

                with(asean){
                    AseanCountryDataMapper.transform(data).let {
                        items.addAll(it)
                    }
                    error?.let { currentThrowable = it }
                }

                return@Function3 items.toList() to currentThrowable
            })
            .observeOn(schedulerProvider.ui()) //go back to ui thread
            .subscribe({ (result, throwable) ->
                _liveItems.postValue(result)

                //For now only check if there is a throwable
                if(throwable != null) _toastMessage.value = Constant.ERROR_MESSAGE
                _loading.value = false
            }, {
                _toastMessage.value = Constant.ERROR_MESSAGE
                showErrorState(it)
            }).addTo(compositeDisposable)
    }
}