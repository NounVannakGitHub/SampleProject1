package kh.com.wbfinance.vannak.sampleproject1.di

import kh.com.wbfinance.vannak.sampleproject1.data.source.generated.AppGeneratedSource
import kh.com.wbfinance.vannak.sampleproject1.data.source.pref.AppPrefSource
import org.koin.dsl.module

val persistenceModule = module {
    single {
        AppPrefSource()
    }

    single {
        AppGeneratedSource()
    }
}