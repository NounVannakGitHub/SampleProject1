package kh.com.wbfinance.vannak.sampleproject1.data.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T> (
    @Expose @SerializedName("data")
    val data: T
)