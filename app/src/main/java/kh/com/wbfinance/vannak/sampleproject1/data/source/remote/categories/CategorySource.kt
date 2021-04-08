package kh.com.wbfinance.vannak.sampleproject1.data.source.remote.categories

open class CategorySource(private val api: CategoryApi){
    fun fetchCategories(querySearch: String,page: Int,limit: Int) = api.fetchCategories(querySearch,page,limit)
    fun fetchCategories(productPresent: Boolean,parentId: Int,page: Int,limit: Int) = api.fetchCategories(productPresent,parentId,page,limit)
}