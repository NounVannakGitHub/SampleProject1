package kh.com.wbfinance.vannak.sampleproject1.model.dao

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryInfo(
    @Expose @SerializedName("_id") val id: Int = 0,
    @Expose @SerializedName("iso2") val iso2: String,
    @Expose @SerializedName("iso3") val iso3: String,
    @Expose @SerializedName("lat") val lat: Double = 0.0,
    @Expose @SerializedName("long") val long: Double = 0.0,
    @Expose @SerializedName("flag") val flag: String
): Parcelable {}