package com.wensolution.wensxendit.apiservice.requestbody

import com.google.gson.annotations.SerializedName

data class EwalletRequestBody(
    val amount: Int,
    val currency: String = "IDR",
    val country: String = "ID",
    @SerializedName("payment_method")
    val paymentMethod: PaymentMethod,
) {
    data class PaymentMethod(
        val type: String = "EWALLET",
        val ewallet: Ewallet,
        @SerializedName("reference_id")
        val referenceId: String,
        val reusability: String = "ONE_TIME_USE"
    ) {
        data class Ewallet(
            @SerializedName("channel_code")
            val channelCode: String,
            @SerializedName("channel_properties")
            val channelProperties: ChannelProperties
        ) {
            data class ChannelProperties(
                @SerializedName("success_return_url")
                val successReturnUrl: String,
                @SerializedName("failure_return_url")
                val failureReturnUrl: String,
                @SerializedName("mobile_number")
                val mobileNumber: String = ""
            )
        }
    }

}