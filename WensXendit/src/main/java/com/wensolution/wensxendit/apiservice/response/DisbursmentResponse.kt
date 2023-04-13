package com.wensolution.wensxendit.apiservice.response

import com.google.gson.annotations.SerializedName

data class DisbursmentResponse(
    val id: String,
    @SerializedName("user_id")
    val userID: String,
    @SerializedName("external_id")
    val externalID: String,
    val amount: Long,
    @SerializedName("bank_code")
    val bankCode: String,
    @SerializedName("account_holder_name")
    val accountHolderName: String,
    @SerializedName("disbursementDescription")
    val disbursementDescription: String,
    val status: String
)
