package kh.com.wbfinance.vannak.sampleproject1.di

import kh.com.wbfinance.vannak.sampleproject1.data.repository.AppRepository
import kh.com.wbfinance.vannak.sampleproject1.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    factory<Repository> {
        AppRepository(get(), get(), get())
    }
}