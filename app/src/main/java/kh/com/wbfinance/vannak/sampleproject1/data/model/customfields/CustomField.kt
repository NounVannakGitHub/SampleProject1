package kh.com.wbfinance.vannak.sampleproject1.data.model.customfields

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CustomField(
    @Json(name = "key") val key: String,
    @Json(name = "value") val value: String
)