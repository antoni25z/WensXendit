package com.wensolution.wensxendit.activity

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.wensolution.wensxendit.databinding.ActivityQrdetailBinding
import com.wensolution.wensxendit.generateBarcode

class QRDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityQrdetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val total = intent.getLongExtra("TOTAL", 0)
        val qrString = intent.getStringExtra("QR_STRING")
        val expire = intent.getStringExtra("EXPIRED")

        binding.totalTxt.text = total.toString()

        generateBarcode(qrString, binding.qrImg)

        binding.expiredTxt.text = expire

        binding.saveQrBtn.setOnClickListener {
            saveBarcodeView()
        }
    }

    private fun saveBarcodeView(){
        val bitmap = Bitmap.createBitmap(binding.qrCodeCard.width, binding.qrCodeCard.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)
        binding.qrCodeCard.draw(canvas)

        val contentValues = contentValuesOf(
            MediaStore.MediaColumns.TITLE to "qris${System.currentTimeMillis()}",
            MediaStore.MediaColumns.DISPLAY_NAME to "qris${System.currentTimeMillis()}",
            MediaStore.MediaColumns.MIME_TYPE to "image/jpeg",
            MediaStore.MediaColumns.DATE_ADDED to System.currentTimeMillis(),
        )
        val resolver = contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        val outputStream = resolver.openOutputStream(uri!!)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream?.flush()
        outputStream?.close()

        Toast.makeText(this, "QR CODE Tersimpan", Toast.LENGTH_SHORT).show()
    }
}