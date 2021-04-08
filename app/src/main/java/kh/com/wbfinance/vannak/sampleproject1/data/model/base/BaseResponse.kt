package kh.com.wbfinance.vannak.sampleproject1.data.model.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    @Json(name = "message") val message: String,
    @Json(name = "data") val data: T
)