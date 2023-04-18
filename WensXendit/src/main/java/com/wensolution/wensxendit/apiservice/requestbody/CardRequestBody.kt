package com.wensolution.wensxendit.apiservice.requestbody

import com.google.gson.annotations.SerializedName

data class CardRequestBody(
    val type: String = "CARD",
    val card: Card,
    val reusability: String = "ONE_TIME_USE",
    @SerializedName("reference_id")
    val referenceId: String,
    val description: String = "Topup",
    val metadata: MetaData = MetaData()
) {
    data class Card(
        val currency: String = "IDR",
        @SerializedName("channel_properties")
        val channelProperties: ChannelProperty = ChannelProperty(),
        @SerializedName("card_information")
        val cardInformation: CardInformation
    ) {
        data class ChannelProperty(
            @SerializedName("success_return_url")
            val successReturnUrl: String = "",
            @SerializedName("failure_return_url")
            val failureReturnUrl: String = ""
        )

        data class CardInformation(
            @SerializedName("card_number")
            val cardNumber: String,
            @SerializedName("expiry_month")
            val expiryMonth: String,
            @SerializedName("expiry_year")
            val expiryYear: String,
            val cvv: String,
            @SerializedName("cardholder_name")
            val cardholderName: String
        )
    }

    data class MetaData(
        val foo: String = "bar"
    )
}
