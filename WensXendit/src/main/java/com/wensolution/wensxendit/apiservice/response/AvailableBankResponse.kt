package com.wensolution.wensxendit.apiservice.response

import com.google.gson.annotations.SerializedName

data class AvailableBankResponse(
    val name: String,
    val code: String,
    @SerializedName("can_disburse")
    val canDisburse: Boolean,
    @SerializedName("can_name_validate")
    val canNameValidate: Boolean
)
