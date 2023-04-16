package com.wensolution.wensxendit
import android.graphics.*
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.nio.charset.Charset
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

fun convertCurrencyFormat(value: Double): String {
    val formatter: NumberFormat = DecimalFormat("#,###")
    val formatterNum = formatter.format(value)
    return formatterNum.replace(",", ".")
}

fun generateBarcode(
    barcode: String?,
    barcodeImg: ImageView
) {
    val hintMap = HashMap<EncodeHintType, ErrorCorrectionLevel>()
    hintMap[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H

    createBarcode(barcode, hintMap, barcodeImg, Charset.defaultCharset())
}

fun createBarcode(
    barcode: String?,
    hintMap: Map<EncodeHintType, ErrorCorrectionLevel>,
    barcodeImg: ImageView,
    charset: Charset,
) {
    val bitmap: Bitmap?
    val multiFormatWriter = MultiFormatWriter()
    try {
        val bitMatrix = multiFormatWriter.encode(
            String(barcode!!.toByteArray(charset)),
            BarcodeFormat.QR_CODE,
            barcodeImg.width,
            barcodeImg.height,
            hintMap
        )
        bitmap = Bitmap.createBitmap(barcodeImg.width, barcodeImg.height, Bitmap.Config.RGB_565)

        for (i in 0 until barcodeImg.width) {
            for (j in 0 until barcodeImg.height) {
                bitmap?.setPixel(i, j, if (bitMatrix.get(i, j)) Color.BLACK else Color.WHITE)
            }
        }

        barcodeImg.setImageBitmap(bitmap)

    } catch (e: WriterException) {
        e.printStackTrace()
    }
}

fun convertServerDateToUserTimeZone(serverDate: String?): String {
    var ourdate: String
    try {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.s'Z'", Locale.US)
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val value = serverDate?.let { formatter.parse(it) }
        val timeZone = TimeZone.getTimeZone("Asia/Kolkata")
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US) //this format changeable
        dateFormatter.timeZone = timeZone
        ourdate = dateFormatter.format(Objects.requireNonNull(value))

    } catch (e: Exception) {
        e.printStackTrace()
        ourdate = "0000-00-00 00:00:00"
    }
    return ourdate
}