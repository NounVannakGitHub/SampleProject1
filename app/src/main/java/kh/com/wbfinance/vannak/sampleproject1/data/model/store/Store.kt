package kh.com.wbfinance.vannak.sampleproject1.data.model.store

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kh.com.wbfinance.vannak.sampleproject1.data.model.customfields.CustomField
import kh.com.wbfinance.vannak.sampleproject1.data.model.medias.Media

@JsonClass(generateAdapter = true)
data class Store(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "delivery_fee") val deliveryFee: Int,
    @Json(name = "phone") val phone: String,
    @Json(name = "custom_fields") val customFields: List<CustomField>,
    @Json(name = "has_media") val hasMedia: Boolean,
    @Json(name = "rate") val rate: String,
    @Json(name = "media") val media: List<Media>
)