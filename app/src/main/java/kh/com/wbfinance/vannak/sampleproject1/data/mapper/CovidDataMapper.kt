package kh.com.wbfinance.vannak.sampleproject1.data.mapper

import kh.com.wbfinance.vannak.sampleproject1.data.helper.CaseTypes
import kh.com.wbfinance.vannak.sampleproject1.model.dao.Country
import kh.com.wbfinance.vannak.sampleproject1.model.dao.CovidDaily
import kh.com.wbfinance.vannak.sampleproject1.model.dao.CovidDetail
import kh.com.wbfinance.vannak.sampleproject1.model.dao.CovidOverview
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.*

object CovidDailyDataMapper {

    fun transform(responses: List<CovidDaily>?) = responses?.map { response ->
        DailyItem(
            response.deltaConfirmed,
            response.deltaRecovered,
            response.mainlandChina,
            response.otherLocations,
            response.reportDate,
            response.incrementRecovered,
            response.incrementConfirmed
        )
    }.orEmpty()
}

object AseanCountryDataMapper {

    fun transform(responses: List<Country>?) = responses?.map { response ->
        CountryItem(
            response.country,
            response.countryInfo,
            response.todayCases,
            response.todayDeaths
        )
    }.orEmpty()
}

object CovidOverviewDataMapper {

    fun transform(response: CovidOverview?) = OverviewItem(
        response?.cases ?: 0,
        response?.active ?: 0,
        response?.recovered ?: 0,
        response?.deaths ?: 0)
}

object CovidPinnedDataMapper {

    fun transform(response: Country?) : PinnedItem? = if(response != null) PinnedItem(
        response.active,
        response.recovered,
        response.deaths,
        response.country,
        response.updated)
    else null
}

object CovidDetailDataMapper {

    fun transform(responses: List<Country>?, @CaseTypes caseType: Int) = responses?.map { response ->
        LocationItem(
            response.active,
            response.recovered,
            response.deaths,
            response.country,
            response.updated,
            response.countryInfo.lat,
            response.countryInfo.long,
            response.country,
            response.continent,
            caseType
        )
    }.orEmpty()
}

object CovidDataMapper{

    fun transformOverviewToUpdatedRegion(covidDetail: Country,update: Country) =  Country(
        updated = covidDetail.updated,
        country = covidDetail.country,
        countryInfo = covidDetail.countryInfo,
        cases = covidDetail.cases,
        todayCases = covidDetail.todayCases,
        deaths = covidDetail.deaths ?: 0,
        todayDeaths = covidDetail.todayDeaths,
        recovered = covidDetail.recovered ?: 0,
        active = covidDetail.active ?: 0,
        critical = covidDetail.critical,
        casesPerOneMillion = covidDetail.casesPerOneMillion,
        deathsPerOneMillion = covidDetail.deathsPerOneMillion,
        tests = covidDetail.tests,
        testsPerOneMillion = covidDetail.testsPerOneMillion
    )
}

//"updated": 1587140875474,
//"country": "Italy",
//"countryInfo": {
//"cases": 168941,
//"todayCases": 3786,
//"deaths": 22170,
//"todayDeaths": 525,
//"recovered": 40164,
//"active": 106607,
//"critical": 2936,
//"casesPerOneMillion": 2794,
//"deathsPerOneMillion": 367,
//"tests": 1178403,
//"testsPerOneMillion": 19490