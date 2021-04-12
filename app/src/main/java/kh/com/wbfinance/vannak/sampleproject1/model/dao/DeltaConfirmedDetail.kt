package kh.com.wbfinance.vannak.sampleproject1.model.dao

import com.google.gson.annotations.SerializedName

data class DeltaConfirmedDetail (
        @SerializedName("china")
        var china: Int = 0,
        @SerializedName("outsideChina")
        var outsideChina: Int = 0,
        @SerializedName("total")
        var total: Int = 0
)