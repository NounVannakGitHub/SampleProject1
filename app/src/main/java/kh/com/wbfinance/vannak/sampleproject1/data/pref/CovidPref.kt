package kh.com.wbfinance.vannak.sampleproject1.data.pref

import com.orhanobut.hawk.Hawk
import kh.com.wbfinance.vannak.sampleproject1.data.helper.CacheKey
import kh.com.wbfinance.vannak.sampleproject1.model.dao.Continent
import kh.com.wbfinance.vannak.sampleproject1.model.dao.Country
import kh.com.wbfinance.vannak.sampleproject1.model.dao.CovidOverview

class CovidPref {
    fun getOverview(): CovidOverview? = Hawk.get(CacheKey.OVERVIEW, null)

    fun setOverview(covidOverview: CovidOverview) = Hawk.put(CacheKey.OVERVIEW, covidOverview)

    fun getContinents() : List<Continent>?  = Hawk.get(CacheKey.CONTINENT,null)

    fun setContinents(continents: List<Continent>) = Hawk.put(CacheKey.CONTINENT,continents)

    fun getContinent(continent: String) : Continent?  = Hawk.get(continent,null)

    fun setContinent(continent: Continent) = Hawk.put(continent.continent,continent)

    fun getAseanCountries() : List<Country>?  = Hawk.get(CacheKey.ASEAN,null)

    fun setAseanCountries(countries: List<Country>) = Hawk.put(CacheKey.ASEAN,countries)

    fun getCountries() : List<Country>?  = Hawk.get(CacheKey.COUNTRY,null)

    fun setCountries(countries: List<Country>) = Hawk.put(CacheKey.COUNTRY,countries)

    fun setCountry(country: Country) = Hawk.put(country.country,country)

    fun getCountry(country: String): Country? = Hawk.get(country, null)
}