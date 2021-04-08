package kh.com.wbfinance.vannak.sampleproject1.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.functions.Function3
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.data.mapper.CovidDailyDataMapper
import kh.com.wbfinance.vannak.sampleproject1.data.mapper.CovidOverviewDataMapper
import kh.com.wbfinance.vannak.sampleproject1.data.mapper.CovidPinnedDataMapper
import kh.com.wbfinance.vannak.sampleproject1.data.model.CovidDaily
import kh.com.wbfinance.vannak.sampleproject1.data.model.CovidDetail
import kh.com.wbfinance.vannak.sampleproject1.data.model.CovidOverview
import kh.com.wbfinance.vannak.sampleproject1.data.repository.Result;
import kh.com.wbfinance.vannak.sampleproject1.data.repository.Repository;
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.ErrorStateItem
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.LoadingStateItem
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.TextItem
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewModel
import kh.com.wbfinance.vannak.sampleproject1.util.Constant
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

        val dailyObservable = appRepository.daily()
            .observeOn(schedulerProvider.io())

        val pinnedObservable = appRepository.pinnedRegion()
            .observeOn(schedulerProvider.io())

        Observable.combineLatest(
            overviewObservable,
            dailyObservable,
            pinnedObservable,
            Function3<Result<CovidOverview>,
                    Result<List<CovidDaily>>,
                    Result<CovidDetail>,
                    Pair<List<BaseViewItem>, Throwable?>> { overview, daily, pinned ->

                val items: MutableList<BaseViewItem> = mutableListOf()
                var currentThrowable: Throwable? = null

                with(overview){
                    items.add(CovidOverviewDataMapper.transform(data))
                    error?.let { currentThrowable = it }
                }

                with(pinned){
                    CovidPinnedDataMapper.transform(data)?.let {
                        items.add(it)
                    }
                    error?.let { currentThrowable = it }
                }

                items.add(TextItem(R.string.per_country))
                items.addAll(appRepository.getPerCountryItem())

                with(daily){
                    val dailies = CovidDailyDataMapper.transform(data)
                    if(dailies.isNotEmpty()) {
                        items.add(TextItem(R.string.daily_updates, R.string.show_graph))
                        items.addAll(dailies)
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