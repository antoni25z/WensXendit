package quicktype

import com.google.gson.annotations.SerializedName

data class CreditCardPaymentResponse (
    val id: String,
    val type: String,
    val country: String,
    @SerializedName("business_id")
    val businessID: String,
    @SerializedName("customer_id")
    val customerID: Any? = null,
    @SerializedName("reference_id")
    val referenceID: String,
    val reusability: String,
    val status: String,
    val actions: List<Any?>,
    val description: String,
    val created: String,
    val updated: String,
    val metadata: Metadata,
    @SerializedName("billing_information")
    val billingInformation: Any? = null,
    @SerializedName("failure_code")
    val failureCode: Any? = null,
    val ewallet: Any? = null,
    @SerializedName("direct_bank_transfer")
    val directBankTransfer: Any? = null,
    @SerializedName("direct_debit")
    val directDebit: Any? = null,
    val card: Card,
    @SerializedName("over_the_counter")
    val overTheCounter: Any? = null,
    @SerializedName("qr_code")
    val qrCode: Any? = null,
    @SerializedName("virtual_code")
    val virtualAccount: Any? = null
) {
    data class Card (
        val currency: String,
        @SerializedName("channel_properties")
        val channelProperties: ChannelProperties,
        @SerializedName("card_information")
        val cardInformation: CardInformation,
        @SerializedName("card_verification_results")
        val cardVerificationResults: Any? = null
    ) {
        data class CardInformation (
            @SerializedName("token_id")
            val tokenID: String,
            @SerializedName("masked_card_number")
            val maskedCardNumber: String,
            @SerializedName("cardholder_name")
            val cardholderName: String,
            @SerializedName("expiry_month")
            val expiryMonth: String,
            @SerializedName("expiry_year")
            val expiryYear: String,
            val type: String,
            val network: String,
            val country: String,
            val issuer: String,
            val fingerprint: String
        )

        data class ChannelProperties (
            @SerializedName("skip_three_d_secure")
            val skipThreeDSecure: Any? = null,
            @SerializedName("success_return_url")
            val successReturnURL: String,
            @SerializedName("failure_return_url")
            val failureReturnURL: String,
            @SerializedName("cardonfile_type")
            val cardonfileType: Any? = null
        )
    }

    data class Metadata (
        val foo: String
    )
}
