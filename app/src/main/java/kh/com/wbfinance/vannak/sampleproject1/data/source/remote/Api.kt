package kh.com.wbfinance.vannak.sampleproject1.data.source.remote

import io.reactivex.Observable
import kh.com.wbfinance.vannak.sampleproject1.data.model.BaseResponse
import kh.com.wbfinance.vannak.sampleproject1.data.model.CovidDaily
import kh.com.wbfinance.vannak.sampleproject1.data.model.CovidDetail
import kh.com.wbfinance.vannak.sampleproject1.data.model.CovidOverview
import kh.com.wbfinance.vannak.sampleproject1.data.model.base.BasePagination
import kh.com.wbfinance.vannak.sampleproject1.data.model.categories.Category
import kh.com.wbfinance.vannak.sampleproject1.data.model.indonesia.IndonesiaDaily
import kh.com.wbfinance.vannak.sampleproject1.data.model.indonesia.IndonesiaPerProvince
import kh.com.wbfinance.vannak.sampleproject1.data.model.products.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

@JvmSuppressWildcards
interface Api {
    @GET("api")
    fun overview(): Observable<CovidOverview>

    @GET("api/daily")
    fun daily(): Observable<List<CovidDaily>>

    @GET("api/confirmed")
    fun confirmed(): Observable<List<CovidDetail>>

    @GET("api/deaths")
    fun deaths(): Observable<List<CovidDetail>>

    @GET("api/recovered")
    fun recovered(): Observable<List<CovidDetail>>

    @GET("api/countries/{country}")
    fun country(@Path("country") country: String): Observable<CovidOverview>

    @GET
    fun getIndonesiaDaily(@Url url: String = "https://indonesia-covid-19.mathdro.id/api/harian"): Observable<BaseResponse<List<IndonesiaDaily>>>

    @GET
    fun getIndonesiaPerProvince(@Url url: String = "https://indonesia-covid-19.mathdro.id/api/provinsi"): Observable<BaseResponse<List<IndonesiaPerProvince>>>

    ///////// CATEGORY ////////////
    @GET("v1/categories?orderBy=order&sortedBy=asc&searchFields=name:like&search=name:{search}")
    fun fetchCategories(
        @Path("search") querySearch: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ) : Observable<kh.com.wbfinance.vannak.sampleproject1.data.model.base.BaseResponse<BasePagination<List<Category>>>>

    @GET("v1/categories?search=type:0&searchFields=type:=&orderBy=order&sortedBy=asc")
    fun fetchCategories(
        @Query("product_present") productPresent: Boolean = false,
        @Query("parent_id") parentId: Int = 0,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ) : Observable<kh.com.wbfinance.vannak.sampleproject1.data.model.base.BaseResponse<BasePagination<List<Category>>>>

    ///////// PRODUCT ////////////
    @GET("v1/products")
    fun getProductsByCategoryId(
        @Query("category_id") categoryId: Int
    ) : Observable<kh.com.wbfinance.vannak.sampleproject1.data.model.base.BaseResponse<BasePagination<List<Product>>>>
}