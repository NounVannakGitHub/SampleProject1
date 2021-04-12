package kh.com.wbfinance.vannak.sampleproject1.model.api

import io.reactivex.Observable
import kh.com.wbfinance.vannak.sampleproject1.model.dao.*
import kh.com.wbfinance.vannak.sampleproject1.model.dao.news.Article
import kh.com.wbfinance.vannak.sampleproject1.model.dao.news.ResponseNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

@JvmSuppressWildcards
interface CovidApi {

    @GET("v2/continents?yesterday&sort")
    fun allContinents() : Observable<List<Continent>>

    @GET("v2/continents/{query}?yesterday&strict")
    fun specificContinent(@Path("query")  continent: String) : Observable<Continent>

    @GET("v2/continents/{query}?yesterday&strict")
    fun multipleContinents(@Path("query")  continents: String) : Observable<List<Continent>>

    @GET("v2/countries?yesterday&sort")
    fun allCountries() : Observable<List<Country>>

    @GET("v2/countries/{query}?yesterday")
    fun multipleCountries(@Path("query")  countries: String) : Observable<List<Country>>

    @GET("v2/countries/Cambodia,Brunei,Laos,Vietnam,Thailand,Myanmar,Malaysia,Singapore,Indonesia,Philippines?yesterday&strict&sort")
    fun aseanCountries() : Observable<List<Country>>

    @GET("v2/countries/{query}?yesterday&strict")
    fun specificCountry(@Path("query") country: String) : Observable<Country>

    @GET("v2/all?yesterday")
    fun overview(): Observable<CovidOverview>

    @GET
    fun allCovidNews(@Url url: String = "https://newsapi.org/v2/everything?q=covid-19&from=2021-03-12&sortBy=publishedAt&apiKey=2c09c65749f04f33a8d51ff1328a0c17"): Observable<ResponseNews<List<Article>>>

}