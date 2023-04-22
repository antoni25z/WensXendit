package com.wensolution.wensxendit.apiservice.requestbody

import com.google.gson.annotations.SerializedName

data class ValidateNameRequestBody(
    @SerializedName("bank_account_number")
    val bankAccountNumber: String,
    @SerializedName("bank_code")
    val bankCode: String
)
