package com.wensolution.wensxendit.apiservice.requestbody

import com.google.gson.annotations.SerializedName

data class EwalletRequestBody(
    val amount: Int,
    val currency: String = "IDR",
    val country: String = "ID",
    @SerializedName("payment_method")
    val paymentMethod : PaymentMethod,
) {
    data class PaymentMethod(
        val type: String = "EWALLET",
        val ewallet: Ewallet,
        val reusability: String = "ONE_TIME_USE"
    )

    data class Ewallet(
        @SerializedName("channel_code")
        val channelCode: String,
        @SerializedName("channel_properties")
        val channelProperties: ChannelProperty
    )

    data class ChannelProperty(
        val successReturnUrl: String
    )

}