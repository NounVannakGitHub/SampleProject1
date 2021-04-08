package kh.com.wbfinance.vannak.sampleproject1.data.model.products

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kh.com.wbfinance.vannak.sampleproject1.data.model.categories.Category
import kh.com.wbfinance.vannak.sampleproject1.data.model.customfields.CustomField
import kh.com.wbfinance.vannak.sampleproject1.data.model.medias.Media
import kh.com.wbfinance.vannak.sampleproject1.data.model.store.Store
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "id") val id: Int,
    @Json(name = "cost") val cost: Double,
    @Json(name = "price") val price: Double,
    @Json(name = "price_range") val priceRange: Double,
    @Json(name = "discount_price") val discountPrice: Double,
    @Json(name = "description") val description: String,
    @Json(name = "capacity") val capacity: String,
    @Json(name = "package_items_count") val packageItemCounts: String,
    @Json(name = "unit") val unit: String,
    @Json(name = "rate") val rate: String,
    @Json(name = "itemsAvailable") val itemsAvailable: String,
    @Json(name = "featured") val featured: Boolean,
    @Json(name = "deliverable") val deliverable: Boolean,
    @Json(name = "store_id") val storeId: Int,
    @Json(name = "category_id") val categoryId: Int,
    @Json(name = "brand_id") val brandId: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "tag") val tag: List<String>,
    @Json(name = "slug") val slug: String,
    @Json(name = "is_negotiate") val isNegotiate: Boolean,
    @Json(name = "code") val code: String,
    @Json(name = "custom_fields") val customFields: List<CustomField>,
    @Json(name = "has_media") val hasMedia: Boolean,
    @Json(name = "store") val store: Store,
    @Json(name = "tags") val tags: List<String>,
    @Json(name = "category") val category: Category,
    @Json(name = "media") val media: List<Media>
): BaseViewItem