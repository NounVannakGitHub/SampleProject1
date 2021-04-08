package kh.com.wbfinance.vannak.sampleproject1.data.source.remote

class AppRemoteSource(private val api: Api) {
    fun overview() = api.overview()

    fun daily() = api.daily()

    fun confirmed() = api.confirmed()

    fun deaths() = api.deaths()

    fun recovered() = api.recovered()

    fun country(id: String) = api.country(id)

    fun indonesiaDaily() = api.getIndonesiaDaily()

    fun indonesiaPerProvince() = api.getIndonesiaPerProvince()

    fun getProductsByCategoryId(categoryId: Int) = api.getProductsByCategoryId(categoryId)

    fun fetchCategories(querySearch: String,page: Int,limit: Int) = api.fetchCategories(querySearch,page,limit)

    fun fetchCategories(productPresent: Boolean,parentId: Int,page: Int,limit: Int) = api.fetchCategories(productPresent,parentId,page,limit)
}