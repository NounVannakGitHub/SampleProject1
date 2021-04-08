package kh.com.wbfinance.vannak.sampleproject1.data.repository.product

import io.reactivex.Observable
import kh.com.wbfinance.vannak.sampleproject1.data.model.base.BasePagination
import kh.com.wbfinance.vannak.sampleproject1.data.model.products.Product

interface Repository {
    /////// PRODUCT /////
    fun getProductsByCategoryId(categoryId: Int) : Observable<BasePagination<List<Product>>>
}