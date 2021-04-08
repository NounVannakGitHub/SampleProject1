package kh.com.wbfinance.vannak.sampleproject1.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.ErrorStateItem
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel(), LifecycleObserver {


    protected val TAG = javaClass.simpleName
    protected val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        with(compositeDisposable) {
            clear()
            dispose()
        }
        super.onCleared()
    }

    fun handleThrowable(throwable: Throwable): BaseViewItem {
        return when(throwable) {
            is ConnectException,
            is SocketTimeoutException,
            is UnknownHostException,
            is IOException -> {
                ErrorStateItem(R.string.connection_error_title, R.string.connection_error_description)
            }
            else -> ErrorStateItem(R.string.general_error_title, R.string.general_error_description)
        }
    }
}