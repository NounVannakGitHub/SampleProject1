package kh.com.wbfinance.vannak.sampleproject1.model.dao.news

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article(
    @Expose @SerializedName("source") val source: Source,
    @Expose @SerializedName("author") val author: String? = null,
    @Expose @SerializedName("title") val title: String,
    @Expose @SerializedName("description") val description: String,
    @Expose @SerializedName("url") val url: String,
    @Expose @SerializedName("urlToImage") val urlToImage: String,
    @Expose @SerializedName("publishedAt") val publishedAt: String,
    @Expose @SerializedName("content") val content: String?
)

data class Source(
    @Expose @SerializedName("id") val id: String? = null,
    @Expose @SerializedName("name") val name: String? = null
)

data class ResponseNews<T>(
    @Expose @SerializedName("status") val status: String? = null,
    @Expose @SerializedName("totalResults") val totalResults: Int? = 0,
    @Expose @SerializedName("articles") val articles: T? = null
)