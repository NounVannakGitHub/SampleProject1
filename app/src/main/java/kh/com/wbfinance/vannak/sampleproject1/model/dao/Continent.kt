package kh.com.wbfinance.vannak.sampleproject1.model.dao

import com.google.gson.annotations.SerializedName

data class Continent (
    @SerializedName("updated")
    var updated: Long = 0,
    @SerializedName("cases")
    var cases: Int = 0,
    @SerializedName("deaths")
    var deaths: Int = 0,
    @SerializedName("todayCases")
    var todayCases: Int = 0,
    @SerializedName("todayDeaths")
    var todayDeaths: Int = 0,
    @SerializedName("recovered")
    var recovered: Int = 0,
    @SerializedName("active")
    var active: Int = 0,
    @SerializedName("critical")
    var critical: Int = 0,
    @SerializedName("continent")
    var continent: String
)