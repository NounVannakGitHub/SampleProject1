package kh.com.wbfinance.vannak.sampleproject1.di

import kh.com.wbfinance.vannak.sampleproject1.model.repo.CovidRepo
import kh.com.wbfinance.vannak.sampleproject1.model.repo.Repository
import org.koin.dsl.module

val repositoryModule = module {
    factory<Repository> {
        CovidRepo(get(), get())
    }
}