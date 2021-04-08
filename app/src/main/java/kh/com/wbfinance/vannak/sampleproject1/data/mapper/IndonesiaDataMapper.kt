package kh.com.wbfinance.vannak.sampleproject1.data.mapper

import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.data.model.indonesia.IndonesiaDaily
import kh.com.wbfinance.vannak.sampleproject1.data.model.indonesia.IndonesiaPerProvince
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.PerCountryDailyGraphItem
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.PerCountryDailyItem
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.PerCountryProvinceGraphItem
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.PerCountryProvinceItem

object IndonesiaDailyDataMapper {

    fun transformToPerCountryDaily(responses: List<IndonesiaDaily>?) = responses?.map { response ->
        PerCountryDailyItem(
            response.fid,
            response.newCasePerDay,
            response.totalDeath,
            response.totalRecover,
            response.totalCase,
            response.date,
            response.days,
            R.string.indonesia_daily_info
        )
    }.orEmpty()

    fun transformIntoCountryDailyGraph(responses: List<IndonesiaDaily>?) = PerCountryDailyGraphItem(
        listData = transformToPerCountryDaily(responses.orEmpty())
    )

    fun transformIntoCountryProvince(responses: List<IndonesiaPerProvince>?) = responses?.map {
        PerCountryProvinceItem(
            it.provinceCode,
            it.provinceName.orEmpty(),
            it.confirmed,
            it.deaths,
            it.recovered
        )
    }.orEmpty()

    fun transformIntoCountryProvinceGraph(responses: List<IndonesiaPerProvince>?) =
        PerCountryProvinceGraphItem(
            listData = transformIntoCountryProvince(responses)
        )
}