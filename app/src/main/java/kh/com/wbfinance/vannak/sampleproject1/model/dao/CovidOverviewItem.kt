package kh.com.wbfinance.vannak.sampleproject1.model.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidOverviewItem(
    @Expose @SerializedName("detail") val detail: String? = null,
    @Expose @SerializedName("value") val value: Int = 0
)