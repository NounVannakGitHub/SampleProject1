package kh.com.wbfinance.vannak.sampleproject1.data.source.remote.products

class ProductSource(private val api: ProductApi) {
    fun getProductsByCategoryId(categoryId: Int) = api.getProductsByCategoryId(categoryId)
}