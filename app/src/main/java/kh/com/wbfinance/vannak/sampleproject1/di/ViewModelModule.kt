package kh.com.wbfinance.vannak.sampleproject1.di

import kh.com.wbfinance.vannak.sampleproject1.ui.dailygraph.ui.main.DailyGraphViewModel
import kh.com.wbfinance.vannak.sampleproject1.ui.detail.DetailViewModel
import kh.com.wbfinance.vannak.sampleproject1.ui.home.HomeViewModel
import kh.com.wbfinance.vannak.sampleproject1.ui.overview.DashboardViewModel
import kh.com.wbfinance.vannak.sampleproject1.ui.percountry.indonesia.CountryIndonesiaViewModel
import kh.com.wbfinance.vannak.sampleproject1.ui.widget.LocationWidgetViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DashboardViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { DailyGraphViewModel(get(), get()) }
    viewModel { CountryIndonesiaViewModel(get(), get()) }
    viewModel { LocationWidgetViewModel(get(), get()) }
    viewModel { HomeViewModel(get(),get()) }
}