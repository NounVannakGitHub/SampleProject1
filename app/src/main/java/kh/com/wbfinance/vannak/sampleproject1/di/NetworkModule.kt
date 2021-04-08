package kh.com.wbfinance.vannak.sampleproject1.di

import io.reactivex.schedulers.Schedulers
import kh.com.wbfinance.vannak.sampleproject1.BuildConfig
import kh.com.wbfinance.vannak.sampleproject1.data.source.remote.Api
import kh.com.wbfinance.vannak.sampleproject1.data.source.remote.AppRemoteSource
import kh.com.wbfinance.vannak.sampleproject1.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient().newBuilder()
            .connectTimeout(Constant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    single {
        AppRemoteSource(get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(Api::class.java)
    }
}