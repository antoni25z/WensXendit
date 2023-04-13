package com.wensolution.wensxendit.apiservice.requestbody

import com.google.gson.annotations.SerializedName

data class CardPayRequestBody (
    val amount: Long,
    val currency: String = "IDR",
    @SerializedName("payment_method_id")
    val paymentMethodID: String,
    val description: String = "Topup",
    val metadata: Metadata = Metadata()
) {
    data class Metadata (
        val foo: String = "bar"
    )
}