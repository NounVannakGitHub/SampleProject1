package kh.com.wbfinance.vannak.sampleproject1.data.model.medias

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kh.com.wbfinance.vannak.sampleproject1.data.model.customfields.CustomProperty

@JsonClass(generateAdapter = true)
data class Media (
    @Json(name = "id") val id: Int,
    @Json(name = "model_type") val modelType: String,
    @Json(name = "model_id") val modelId: Int,
    @Json(name = "collection_name") val collectionName: String,
    @Json(name = "name") val name: String,
    @Json(name = "file_name") val fileName: String,
    @Json(name = "mime_type") val mimeType: String,
    @Json(name = "disk") val disk: String,
    @Json(name = "size") val size: Int,
    @Json(name = "custom_properties") val customProperties: CustomProperty,
    @Json(name = "order_column") val orderColumn: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "url") val url: String,
    @Json(name = "thumb") val thumb: String,
    @Json(name = "icon") val icon: String,
    @Json(name = "formated_size") val formatedSize: String
)