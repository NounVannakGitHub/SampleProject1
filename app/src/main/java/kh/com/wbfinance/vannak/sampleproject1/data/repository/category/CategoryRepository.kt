package kh.com.wbfinance.vannak.sampleproject1.data.repository.category

import io.reactivex.Observable
import kh.com.wbfinance.vannak.sampleproject1.data.model.base.BasePagination
import kh.com.wbfinance.vannak.sampleproject1.data.model.categories.Category
import kh.com.wbfinance.vannak.sampleproject1.data.source.remote.categories.CategorySource

class CategoryRepository(private val api: CategorySource) : Repository {
    override fun fetchCategories(
        querySearch: String,
        page: Int,
        limit: Int
    ): Observable<BasePagination<List<Category>>> = api.fetchCategories(querySearch,page,limit).map { it.data }

    override fun fetchCategories(
        productPresent: Boolean,
        parentId: Int,
        page: Int,
        limit: Int
    ): Observable<BasePagination<List<Category>>> = api.fetchCategories(productPresent,parentId,page,limit).map { it.data }
}