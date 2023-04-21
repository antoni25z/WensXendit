package com.wensolution.wensxendit.apiservice.response

import com.google.gson.annotations.SerializedName

data class IlumaAvailableBankResponse(
    val name: String,
    val code: String,
    @SerializedName("swift_code")
    val swiftCode: String,
    val remark: String? = null
)
