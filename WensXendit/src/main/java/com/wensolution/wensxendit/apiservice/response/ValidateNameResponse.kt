package com.wensolution.wensxendit.apiservice.response

import com.google.gson.annotations.SerializedName

data class ValidateNameResponse (
    val status: String,
    @SerializedName("bank_account_number")
    val bankAccountNumber: String,
    @SerializedName("bank_code")
    val bankCode: String,
    public val created: String,
    val updated: String,
    val id: String,
    val result: Result
)

data class Result (
    @SerializedName("is_found")
    val isFound: Boolean,
    @SerializedName("is_virtual_account")
    val isVirtualAccount: Boolean,
    @SerializedName("need_review")
    val needReview: Boolean,
    @SerializedName("account_holder_name")
    val accountHolderName: String
)
