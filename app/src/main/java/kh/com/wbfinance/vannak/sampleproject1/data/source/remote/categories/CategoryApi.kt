package kh.com.wbfinance.vannak.sampleproject1.data.source.remote.categories

import io.reactivex.Observable
import kh.com.wbfinance.vannak.sampleproject1.data.model.base.BasePagination
import kh.com.wbfinance.vannak.sampleproject1.data.model.base.BaseResponse
import kh.com.wbfinance.vannak.sampleproject1.data.model.categories.Category
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryApi {
    @GET("v1/categories?orderBy=order&sortedBy=asc&searchFields=name:like&search=name:{search}")
    fun fetchCategories(
        @Path("search") querySearch: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ) : Observable<BaseResponse<BasePagination<List<Category>>>>

    @GET("v1/categories?search=type:0&searchFields=type:=&orderBy=order&sortedBy=asc")
    fun fetchCategories(
        @Query("product_present") productPresent: Boolean = false,
        @Query("parent_id") parentId: Int = 0,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ) : Observable<BaseResponse<BasePagination<List<Category>>>>
}