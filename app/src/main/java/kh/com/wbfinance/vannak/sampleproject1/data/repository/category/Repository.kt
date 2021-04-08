package kh.com.wbfinance.vannak.sampleproject1.data.repository.category

import io.reactivex.Observable
import kh.com.wbfinance.vannak.sampleproject1.data.model.base.BasePagination
import kh.com.wbfinance.vannak.sampleproject1.data.model.categories.Category

interface Repository {
    /////// CATEGORY /////
    fun fetchCategories(querySearch: String,page: Int,limit: Int) : Observable<BasePagination<List<Category>>>
    fun fetchCategories(productPresent: Boolean,parentId: Int,page: Int,limit: Int) : Observable<BasePagination<List<Category>>>
}