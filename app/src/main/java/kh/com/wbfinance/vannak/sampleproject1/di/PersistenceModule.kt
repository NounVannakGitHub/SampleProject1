package kh.com.wbfinance.vannak.sampleproject1.di

import kh.com.wbfinance.vannak.sampleproject1.data.pref.CovidPref
import org.koin.dsl.module

val persistenceModule = module {
    single {
        CovidPref()
    }
}