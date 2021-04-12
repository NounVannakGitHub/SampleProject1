package kh.com.wbfinance.vannak.sampleproject1.model.dao

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country (
    @Expose @SerializedName("updated")val updated: Long = 0,
    @Expose @SerializedName("country")val country: String,
    @Expose @SerializedName("countryInfo")val countryInfo: CountryInfo,
    @Expose @SerializedName("cases")val cases: Int = 0,
    @Expose @SerializedName("active")val active: Int = 0,
    @Expose @SerializedName("todayCases") val todayCases: Int = 0,
    @Expose @SerializedName("deaths")val deaths: Int = 0,
    @Expose @SerializedName("todayDeaths")val todayDeaths: Int = 0,
    @Expose @SerializedName("recovered")val recovered: Int = 0,
    @Expose @SerializedName("critical")val critical: Int = 0,
    @Expose @SerializedName("tests")val tests: Int = 0,
    @Expose @SerializedName("population")val population: Int = 0,
    @Expose @SerializedName("continent")val continent: String? = null,
    @Expose @SerializedName("testsPerOneMillion")val testsPerOneMillion: Double = 0.0,
    @Expose @SerializedName("casesPerOneMillion")val casesPerOneMillion: Double = 0.0,
    @Expose @SerializedName("deathsPerOneMillion")val deathsPerOneMillion: Double = 0.0,
    @Expose @SerializedName("oneCasePerPeople")val oneCasePerPeople: Double = 0.0,
    @Expose @SerializedName("oneDeathPerPeople")val oneDeathPerPeople: Double = 0.0,
    @Expose @SerializedName("oneTestPerPeople")val oneTestPerPeople: Double = 0.0,
    @Expose @SerializedName("activePerOneMillion")val activePerOneMillion: Double = 0.0,
    @Expose @SerializedName("recoveredPerOneMillion") val recoveredPerOneMillion: Double = 0.0,
    @Expose @SerializedName("criticalPerOneMillion") val criticalPerOneMillion: Double = 0.0
) : Parcelable {
    val locationName get() = country + if (!country.isEmpty()) ", $country" else ""
    val compositeKey get() = country + continent
}

