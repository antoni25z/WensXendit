package com.wensolution.wensxendit.apiservice.requestbody

import com.google.gson.annotations.SerializedName

data class VaRequestBody(
    val currency: String = "IDR",
    val amount: Int,
    @SerializedName("payment_method")
    val paymentMethod : PaymentMethod,
    val metadata: MetaData = MetaData()
) {
    data class PaymentMethod(
        val type: String = "VIRTUAL_ACCOUNT",
        val reusability: String = "ONE_TIME_USE",
        @SerializedName("reference_id")
        val referenceId: String,
        @SerializedName("virtual_account")
        val virtualAccount: VirtualAccount
    ) {
        data class VirtualAccount(
            @SerializedName("channel_code")
            val channelCode: String,
            @SerializedName("channel_properties")
            val channelProperties: ChannelProperty
        ) {
            data class ChannelProperty(
                @SerializedName("customer_name")
                val customerName: String
            )
        }
    }

    data class MetaData(
        val sku: String = "ABCDEFGH"
    )
}
