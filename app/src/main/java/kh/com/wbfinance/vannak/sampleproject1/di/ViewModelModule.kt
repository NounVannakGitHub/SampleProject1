package kh.com.wbfinance.vannak.sampleproject1.di

import kh.com.wbfinance.vannak.sampleproject1.ui.detail.DetailViewModel
import kh.com.wbfinance.vannak.sampleproject1.ui.overview.DashboardViewModel
import kh.com.wbfinance.vannak.sampleproject1.ui.percountry.PerCountryActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DashboardViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { PerCountryActivityViewModel(get(),get()) }
}