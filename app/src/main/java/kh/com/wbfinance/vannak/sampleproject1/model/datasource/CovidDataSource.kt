package kh.com.wbfinance.vannak.sampleproject1.model.datasource

import kh.com.wbfinance.vannak.sampleproject1.model.api.CovidApi

class CovidDataSource(private val api: CovidApi) {
    fun allContinents() = api.allContinents()

    fun specificContinent(continent: String) = api.specificContinent(continent)

    fun multipleContinents(continents: String) = api.multipleContinents(continents)

    fun allCountries() = api.allCountries()

    fun multipleCountries(countries: String) = api.multipleCountries(countries)

    fun aseanCountries() = api.aseanCountries()

    fun specificCountry(country: String) = api.specificCountry(country)

    fun overview() = api.overview()

    fun allCovidNews() = api.allCovidNews()
}