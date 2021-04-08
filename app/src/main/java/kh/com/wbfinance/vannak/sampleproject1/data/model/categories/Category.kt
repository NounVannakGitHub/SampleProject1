package kh.com.wbfinance.vannak.sampleproject1.data.model.categories

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kh.com.wbfinance.vannak.sampleproject1.data.model.customfields.CustomField
import kh.com.wbfinance.vannak.sampleproject1.data.model.medias.Media
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "id") val id: Int,
    @Json(name = "code") val code: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "_lft") val _lft: Int,
    @Json(name = "_rgt") val _rgt: Int,
    @Json(name = "parent_id") val parentId: Int,
    @Json(name = "type") val type: String,
    @Json(name = "order") val order: String,
    @Json(name = "slug") val slug: String,
    @Json(name = "custom_fields") val customFields: List<CustomField>,
    @Json(name = "has_media") val hasMedia: Boolean,
    @Json(name = "media") val media: List<Media>
): BaseViewItem