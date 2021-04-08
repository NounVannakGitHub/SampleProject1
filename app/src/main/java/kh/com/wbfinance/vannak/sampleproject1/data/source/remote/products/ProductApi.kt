package kh.com.wbfinance.vannak.sampleproject1.data.source.remote.products

import io.reactivex.Observable
import kh.com.wbfinance.vannak.sampleproject1.data.model.base.BasePagination
import kh.com.wbfinance.vannak.sampleproject1.data.model.base.BaseResponse
import kh.com.wbfinance.vannak.sampleproject1.data.model.products.Product
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("v1/products")
    fun getProductsByCategoryId(
        @Query("category_id") categoryId: Int
    ) : Observable<BaseResponse<BasePagination<List<Product>>>>
}