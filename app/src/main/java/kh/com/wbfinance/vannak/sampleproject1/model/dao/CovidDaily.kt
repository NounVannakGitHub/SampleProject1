package kh.com.wbfinance.vannak.sampleproject1.model.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kh.com.wbfinance.vannak.sampleproject1.data.helper.IncrementStatus

data class CovidDaily (
        @SerializedName("active")
    var active: Int = 0,
        @SerializedName("confirmed")
    var confirmed: Confirm = Confirm(),
        @SerializedName("deaths")
    var deaths: Death = Death(),
        @SerializedName("deltaConfirmed")
    var deltaConfirmed: Int = 0,
        @SerializedName("deltaConfirmedDetail")
    var deltaConfirmedDetail: DeltaConfirmedDetail = DeltaConfirmedDetail(),
        @SerializedName("deltaRecovered")
    var deltaRecovered: Int = 0,
        @SerializedName("incidentRate")
    var incidentRate: Int = 0,
        @SerializedName("mainlandChina")
    var mainlandChina: Int = 0,
        @SerializedName("otherLocations")
    var otherLocations: Int = 0,
        @SerializedName("peopleTested")
    var peopleTested: Int = 0,
        @SerializedName("recovered")
    var recovered: Recovered = Recovered(),
        @SerializedName("reportDate")
    var reportDate: String = "",
        @SerializedName("totalConfirmed")
    var totalConfirmed: Int = 0,
        @SerializedName("totalRecovered")
    var totalRecovered: Int = 0,
        @Expose
    @SerializedName("incrementRecovered")
    var incrementRecovered: Int = IncrementStatus.FLAT,
        @Expose
    @SerializedName("incrementConfirmed")
    var incrementConfirmed: Int = IncrementStatus.FLAT
)