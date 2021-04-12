package kh.com.wbfinance.vannak.sampleproject1.model.repo

import io.reactivex.Completable
import io.reactivex.Observable
import kh.com.wbfinance.vannak.sampleproject1.data.base.BaseResult
import kh.com.wbfinance.vannak.sampleproject1.model.dao.*

interface Repository {

    fun overview(): Observable<BaseResult<CovidOverview>>

    fun allContinents(): Observable<BaseResult<List<Continent>>>
    fun specificContinent(continent: String): Observable<BaseResult<Continent>>
    fun multipleContinents(continents: String): Observable<BaseResult<List<Continent>>>

    fun aseanCountries(): Observable<BaseResult<List<Country>>>
    fun specificCountry(country: String) : Observable<BaseResult<Country>>
    fun allCountries(): Observable<BaseResult<List<Country>>>
    fun multipleCountries(countries: String): Observable<BaseResult<List<Country>>>

    fun pinnedRegion(): Observable<BaseResult<Country>>
    fun putPinnedRegion(data: Country): Completable
    fun removePinnedRegion(): Completable
    fun getCachePinnedRegion(): Country?

    fun getCacheDaily(): List<CovidDaily>

    fun daily(): Observable<BaseResult<List<CovidDaily>>>

    fun country(name: String): Observable<Country>

    fun recovered(): Observable<List<Country>>

    fun deaths(): Observable<List<Country>>

    fun confirmed(): Observable<List<Country>>

    fun fullStats(): Observable<List<Country>>

}