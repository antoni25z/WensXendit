package com.wensolution.wensxendit.apiservice.response

import com.google.gson.annotations.SerializedName

data class PaymentRequestResponse (
    val actions: List<Action>,
    val amount: Long,
    @SerializedName("business_id")
    val businessID: String,
    @SerializedName("capture_method")
    val captureMethod: String,
    @SerializedName("card_verification_results")
    val cardVerificationResults: Any? = null,
    @SerializedName("channel_properties")
    val channelProperties: Any? = null,
    val country: String,
    val created: String,
    val currency: String,
    @SerializedName("customer_id")
    val customerID: Any? = null,
    val description: Any? = null,
    @SerializedName("failure_code")
    val failureCode: Any? = null,
    val id: String,
    val initiator: Any? = null,
    val items: Any? = null,
    val metadata: Any? = null,
    @SerializedName("payment_method")
    val paymentMethod: PaymentMethod,
    @SerializedName("reference_id")
    val referenceID: String,
    @SerializedName("shipping_information")
    val shippingInformation: Any? = null,
    val status: String,
    val updated: String
) {
    data class Action (
        val action: String,
        val method: String? = null,
        @SerializedName("qr_code")
        val qrCode: String? = null,
        val url: String? = null,
        @SerializedName("url_type")
        val urlType: String? = null
    )

    data class PaymentMethod (
        val card: Card?,
        val created: String,
        val description: Any? = null,
        @SerializedName("direct_bank_transfer")
        val directBankTransfer: Any? = null,
        @SerializedName("direct_debit")
        val directDebit: Any? = null,
        val ewallet: Ewallet?,
        @SerializedName("virtual")
        val id: String,
        val metadata: Any? = null,
        @SerializedName("over_the_counter")
        val overTheCounter: Any? = null,
        @SerializedName("qr_code")
        val qrCode: QRCode?,
        @SerializedName("reference_id")
        val referenceID: String,
        val reusability: String,
        val status: String,
        val type: String,
        val updated: String,
        @SerializedName("virtual_account")
        val virtualAccount: VirtualAccount? = null
    ) {
        data class Ewallet (
            val account: Account,
            @SerializedName("channel_code")
            val channelCode: String,
            @SerializedName("channel_properties")
            val channelProperties: ChannelProperties
        ) {
            data class Account (
                @SerializedName("account_details")
                val accountDetails: Any? = null,
                val balance: Any? = null,
                val name: Any? = null,
                @SerializedName("point_balance")
                val pointBalance: Any? = null
            )

            data class ChannelProperties (
                @SerializedName("success_return_url")
                val successReturnURL: String
            )
        }

        data class VirtualAccount(
            val amount: Int,
            @SerializedName("channel_code")
            val channelCode: String,
            @SerializedName("channel_properties")
            val channelProperties: ChannelProperties
        ) {
            data class ChannelProperties(
                @SerializedName("customer_name")
                val customerName: String,
                @SerializedName("expires_at")
                val expiresAt: String,
                @SerializedName("virtual_account_number")
                val virtualAccountNumber: String
            )
        }

        data class QRCode(
            val amount: Int,
            @SerializedName("channel_code")
            val channelCode: String,
            @SerializedName("channel_properties")
            val channelProperties: ChannelProperties,
            val currency: String
        ) {
            data class ChannelProperties(
                @SerializedName("expires_at")
                val expiresAt: String,
                @SerializedName("qr_string")
                val qrString: String
            )
        }

        data class Card (
            @SerializedName("card_information")
            val cardInformation: CardInformation,
            @SerializedName("card_verification_results")
            val cardVerificationResults: Any? = null,
            @SerializedName("channel_properties")
            val channelProperties: ChannelProperties,
            val currency: String
        ) {
            data class CardInformation (
                @SerializedName("cardholder_name")
                val cardholderName: String,
                val country: String,
                @SerializedName("expiry_month")
                val expiryMonth: String,
                @SerializedName("expiry_year")
                val expiryYear: String,
                val fingerprint: String,
                val issuer: String,
                @SerializedName("masked_card_number")
                val maskedCardNumber: String,
                val network: String,
                val tokenID: String,
                val type: String
            )

            data class ChannelProperties (
                @SerializedName("cardonfile_type")
                val cardonfileType: Any? = null,
                @SerializedName("failure_return_url")
                val failureReturnURL: String,
                @SerializedName("skip_three_d_secure")
                val skipThreeDSecure: Any? = null,
                @SerializedName("success_return_url")
                val successReturnURL: String
            )
        }
    }
}


