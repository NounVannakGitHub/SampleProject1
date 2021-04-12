package kh.com.wbfinance.vannak.sampleproject1.data.mapper

import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.model.dao.Country
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.PerCountryDailyGraphItem
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.PerCountryDailyItem
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.PerCountryProvinceGraphItem
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.PerCountryProvinceItem

object CountryDataMapper {

    fun transformToPerCountryDaily(responses: List<Country>?) = responses?.map { response ->
        PerCountryDailyItem(
            response.countryInfo.id,
            response.todayCases,
            response.deaths,
            response.recovered,
            response.cases,
            response.updated,
            R.string.country_daily_info
        )
    }.orEmpty()

    fun transformToCountry(response: Country?) = PerCountryDailyItem(
        response?.countryInfo?.id!!,
        response.todayCases,
        response.deaths,
        response.recovered,
        response.cases,
        response.updated,
        R.string.country_daily_info
    )

    fun transformIntoCountryDailyGraph(responses: List<Country>?) = PerCountryDailyGraphItem(
        listData = transformToPerCountryDaily(responses.orEmpty())
    )

    fun transformIntoCountryProvince(responses: List<Country>?) = responses?.map {
        PerCountryProvinceItem(
            it.countryInfo.id,
            it.country,
            it.cases,
            it.deaths,
            it.recovered
        )
    }.orEmpty()

    fun transformIntoCountryProvinceGraph(responses: List<Country>?) =
        PerCountryProvinceGraphItem(
            listData = transformIntoCountryProvince(responses)
        )
}