package com.wensolution.wensxendit.apiservice.requestbody

import com.google.gson.annotations.SerializedName

data class DisbursmentRequestBody(
    @SerializedName("external_id")
    val externalID: String,
    val amount: Long,
    @SerializedName("bank_code")
    val bankCode: String,
    @SerializedName("account_holder_name")
    val accountHolderName: String,
    @SerializedName("account_number")
    val accountNumber: String,
    val description: String
)
