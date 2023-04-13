package com.wensolution.wensxendit.apiservice.requestbody

import com.google.gson.annotations.SerializedName

data class QRRequestBody(
    val amount: Int,
    val currency: String = "IDR",
    @SerializedName("payment_method")
    val paymentMethod : PaymentMethod = PaymentMethod(),
    val description: String = "Topup",
    val metadata: MetaData = MetaData()
) {
    data class PaymentMethod(
        val type: String = "QR_CODE",
        @SerializedName("qr_code")
        val qrCode: QRCode = QRCode(),
        val reusability: String = "ONE_TIME_USE",

    )

    data class QRCode(
        @SerializedName("channel_code")
        val channelCode: String = "DANA",
    )
    data class MetaData(
        val foo: String = "bar"
    )
}
