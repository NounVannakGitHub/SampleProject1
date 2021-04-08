package kh.com.wbfinance.vannak.sampleproject1.data.model.customfields

import com.squareup.moshi.Json

data class CustomProperty(
    @Json(name = "uuid") val uuid: String,
    @Json(name = "user_id") val userId: Int,
    @Json(name = "generated_conversions") val generatedConversions: GenerateConversion
)