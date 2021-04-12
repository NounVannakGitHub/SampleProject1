package kh.com.wbfinance.vannak.sampleproject1.data.base

import io.reactivex.Observable
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

data class BaseResult<out T>(
        val data: T? = null,
        val error: Throwable? = null
)

fun <T> Observable<T>.responseToResult(): Observable<BaseResult<T>> {
    return map { it.asResult() }
            .onErrorReturn {
                when (it) {
                    is HttpException,
                    is SocketTimeoutException,
                    is UnknownHostException -> {
                        it.asErrorResult()
                    }
                    else -> throw it
                }
            }
}

fun <T> T.asResult(): BaseResult<T> = BaseResult(data = this, error = null)
fun <T> Throwable.asErrorResult(): BaseResult<T> = BaseResult(data = null, error = this)