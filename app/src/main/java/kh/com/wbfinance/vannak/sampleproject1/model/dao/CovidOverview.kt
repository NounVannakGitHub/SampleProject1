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