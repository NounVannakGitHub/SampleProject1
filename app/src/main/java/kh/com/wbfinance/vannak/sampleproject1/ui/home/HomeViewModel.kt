package kh.com.wbfinance.vannak.sampleproject1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kh.com.wbfinance.vannak.sampleproject1.data.model.categories.Category
import kh.com.wbfinance.vannak.sampleproject1.data.repository.Repository
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.LoadingStateItem
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewModel
import kh.com.wbfinance.vannak.sampleproject1.util.Constant
import kh.com.wbfinance.vannak.sampleproject1.util.SingleLiveEvent
import kh.com.wbfinance.vannak.sampleproject1.util.ext.addTo
import kh.com.wbfinance.vannak.sampleproject1.util.rx.SchedulerProvider

class HomeViewModel(
    private val appRepository: Repository,
    private val schedulerProvider: SchedulerProvider
    ): BaseViewModel() {

    private var categories = listOf<Category>()
    private var _currentPage = 1
    private var _limit = 10
    private var _parentId = 0

    private val _categoryListViewItems = MutableLiveData<List<BaseViewItem>>()
    val categoryListViewItems: LiveData<List<BaseViewItem>>
        get() = _categoryListViewItems

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    val errorMessage = SingleLiveEvent<String>()

    fun fetchCategoryWithExistProductInside(){
        _categoryListViewItems.value = listOf(LoadingStateItem())
        appRepository.fetchCategories(true,_parentId,_currentPage,_limit)
            .observeOn(schedulerProvider.io())
            .map {
                categories = it.data.data
                _currentPage = it.data.currentPage
                println(it.data.total)
            }
            .subscribeOn(schedulerProvider.ui())
            .subscribe(
                // NEXT
                {
//                     _categoryListViewItems.postValue(it)
                },
                // ERROR
                {
                    it.printStackTrace()
                    errorMessage.postValue(Constant.ERROR_MESSAGE)
                }
            ).addTo(compositeDisposable)
    }
}