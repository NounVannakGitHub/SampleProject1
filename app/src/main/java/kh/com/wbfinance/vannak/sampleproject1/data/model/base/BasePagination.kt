package kh.com.wbfinance.vannak.sampleproject1.data.model.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BasePagination<T>(
    @Json(name = "first_page_url") val firstPageUrl: String,
    @Json(name = "from") val from: Int,
    @Json(name = "last_page") val last: Int,
    @Json(name = "last_page_url") val lastPageUrl: String,
    @Json(name = "next_page_url") val nextPageUrl: String,
    @Json(name = "path") val path: String,
    @Json(name = "per_page") val perPage: Int,
    @Json(name = "prev_page_url") val prevPageUrl: String,
    @Json(name = "current_page") val currentPage: Int,
    @Json(name = "to") val to: Int,
    @Json(name = "total") val total: Int,
    @Json(name = "data") val data: T
)