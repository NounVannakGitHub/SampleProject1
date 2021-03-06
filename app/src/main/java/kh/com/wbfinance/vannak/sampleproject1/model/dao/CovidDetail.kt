package kh.com.wbfinance.vannak.sampleproject1.model.dao

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CovidDetail(
        @Expose @SerializedName("confirmed") val confirmed: Int = 0,
        @Expose @SerializedName("countryRegion") val countryRegion: String,
        @Expose @SerializedName("deaths") val deaths: Int = 0,
        @Expose @SerializedName("lastUpdate") val lastUpdate: Long = 0,
        @Expose @SerializedName("lat") val lat: Double = 0.0,
        @Expose @SerializedName("long") val long: Double = 0.0,
        @Expose @SerializedName("provinceState") val provinceState: String? = null,
        @Expose @SerializedName("recovered") val recovered: Int = 0,
        @Expose @SerializedName("iso2") val iso2: String? = null
) : Parcelable {
    val locationName get() = countryRegion + if (!provinceState.isNullOrEmpty()) ", $provinceState" else ""
    val compositeKey get() = countryRegion + provinceState
}