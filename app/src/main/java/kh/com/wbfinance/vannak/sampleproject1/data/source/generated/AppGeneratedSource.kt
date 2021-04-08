package kh.com.wbfinance.vannak.sampleproject1.data.source.generated

import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.PerCountryItem

class AppGeneratedSource {

    fun getPerCountryItem() = listOf(
        PerCountryItem(
            PerCountryItem.ID,
            R.string.country_indonesia,
            "https://indonesia-covid-19.mathdro.id/api",
            R.drawable.flag_indonesia
        )
    )
}