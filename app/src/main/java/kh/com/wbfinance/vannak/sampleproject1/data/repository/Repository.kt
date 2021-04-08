package kh.com.wbfinance.vannak.sampleproject1.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import kh.com.wbfinance.vannak.sampleproject1.data.model.CovidDaily
import kh.com.wbfinance.vannak.sampleproject1.data.model.CovidDetail
import kh.com.wbfinance.vannak.sampleproject1.data.model.CovidOverview
import kh.com.wbfinance.vannak.sampleproject1.data.model.base.BasePagination
import kh.com.wbfinance.vannak.sampleproject1.data.model.categories.Category
import kh.com.wbfinance.vannak.sampleproject1.data.model.indonesia.IndonesiaDaily
import kh.com.wbfinance.vannak.sampleproject1.data.model.indonesia.IndonesiaPerProvince
import kh.com.wbfinance.vannak.sampleproject1.data.model.products.Product
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.PerCountryItem

interface Repository {
    fun overview(): Observable<Result<CovidOverview>>
    fun daily(): Observable<Result<List<CovidDaily>>>
    fun confirmed(): Observable<List<CovidDetail>>
    fun deaths(): Observable<List<CovidDetail>>
    fun recovered(): Observable<List<CovidDetail>>
    fun country(id: String): Observable<CovidOverview>
    fun fullStats(): Observable<List<CovidDetail>>
    fun pinnedRegion(): Observable<Result<CovidDetail>>
    fun putPinnedRegion(data: CovidDetail): Completable
    fun removePinnedRegion(): Completable
    fun getCachePinnedRegion(): CovidDetail?
    fun getCacheOverview(): CovidOverview?
    fun getCacheDaily(): List<CovidDaily>?
    fun getCacheConfirmed(): List<CovidDetail>?
    fun getCacheDeath(): List<CovidDetail>?
    fun getCacheRecovered(): List<CovidDetail>?
    fun getCacheFull(): List<CovidDetail>?
    fun getCacheCountry(id: String): CovidOverview?
    fun getPerCountryItem(): List<PerCountryItem>
    fun getIndonesiaDaily(): Observable<List<IndonesiaDaily>>
    fun getIndonesiaPerProvince(): Observable<List<IndonesiaPerProvince>>
    ///////// CATEGORY ////////////
    fun fetchCategories(
        querySearch: String,
        page: Int,
        limit: Int
    ) : Observable<kh.com.wbfinance.vannak.sampleproject1.data.model.base.BaseResponse<BasePagination<List<Category>>>>

    fun fetchCategories(
        productPresent: Boolean,
        parentId: Int,
        page: Int,
        limit: Int
    ) : Observable<kh.com.wbfinance.vannak.sampleproject1.data.model.base.BaseResponse<BasePagination<List<Category>>>>

    ///////// PRODUCT ////////////
    fun getProductsByCategoryId(
        categoryId: Int
    ) : Observable<kh.com.wbfinance.vannak.sampleproject1.data.model.base.BaseResponse<BasePagination<List<Product>>>>
}