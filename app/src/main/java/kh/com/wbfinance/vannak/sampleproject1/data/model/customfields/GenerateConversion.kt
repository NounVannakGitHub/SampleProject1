package kh.com.wbfinance.vannak.sampleproject1.data.model.customfields

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerateConversion(
    @Json(name = "thumb") val thumb: Boolean,
    @Json(name = "icon") val icon: Boolean
)