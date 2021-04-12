package kh.com.wbfinance.vannak.sampleproject1.model.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidOverview(
   @Expose @SerializedName("updated") val updated: Long = 0,
   @Expose @SerializedName("cases") val cases: Int = 0,
   @Expose @SerializedName("todayCases") val todayCases: Int = 0,
   @Expose @SerializedName("deaths") val deaths: Int = 0,
   @Expose @SerializedName("todayDeaths") val todayDeaths: Int = 0,
   @Expose @SerializedName("recovered") val recovered: Int = 0,
   @Expose @SerializedName("active") val active: Int = 0,
   @Expose @SerializedName("critical") val critical: Int = 0,
   @Expose @SerializedName("casesPerOneMillion") val casesPerOneMillion: Double = 0.0,
   @Expose @SerializedName("deathsPerOneMillion") val deathsPerOneMillion: Double = 0.0,
   @Expose @SerializedName("tests") val tests: Int = 0,
   @Expose @SerializedName("testsPerOneMillion") val testsPerOneMillion: Double = 0.0,
   @Expose @SerializedName("affectedCountries") val affectedCountries: Int = 0
)

/*
*
* {
    "updated": 1587140275989,
    "cases": 2181308,
    "todayCases": 95022,
    "deaths": 145471,
    "todayDeaths": 6996,
    "recovered": 547069,
    "active": 1488768,
    "critical": 56602,
    "casesPerOneMillion": 280,
    "deathsPerOneMillion": 18,
    "tests": 18215929,
    "testsPerOneMillion": 2338.3,
    "affectedCountries": 212
}
*
* */