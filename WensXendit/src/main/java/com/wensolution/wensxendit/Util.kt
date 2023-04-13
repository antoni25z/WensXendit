package com.wensolution.wensxendit

import android.content.res.Resources
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

fun convertCurrencyFormat(value: Double): String {
    val formatter: NumberFormat = DecimalFormat("#.###")
    return formatter.format(value)
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