package kh.com.wbfinance.vannak.sampleproject1.data.repository.product

import io.reactivex.Observable
import kh.com.wbfinance.vannak.sampleproject1.data.model.base.BasePagination
import kh.com.wbfinance.vannak.sampleproject1.data.model.products.Product
import kh.com.wbfinance.vannak.sampleproject1.data.source.remote.products.ProductSource

class ProductRepository(private val api: ProductSource) : Repository {
    override fun getProductsByCategoryId(
        categoryId: Int
    ): Observable<BasePagination<List<Product>>> = api.getProductsByCategoryId(categoryId).map { it.data }
}