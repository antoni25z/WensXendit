package com.wensolution.wensxendit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.wensolution.wensxendit.IntentKey
import com.wensolution.wensxendit.SuccessActivity
import com.wensolution.wensxendit.databinding.ActivityCheckOutWebBinding

class CheckOutWebActivity : AppCompatActivity() {

    lateinit var binding: ActivityCheckOutWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckOutWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra(IntentKey.URL_KEY)
        val updateDate = intent.getStringExtra(IntentKey.UPDATED_DATE)
        val paymentCode = intent.getStringExtra(IntentKey.PAYMENT_CODE)
        val referenceId = intent.getStringExtra(IntentKey.REFERENCE_ID)
        val total = intent.getLongExtra(IntentKey.TOTAL, 0)

        if (url != null) {
            binding.webview.loadUrl(url)
            binding.webview.settings.javaScriptEnabled = true
            binding.webview.settings.loadsImagesAutomatically = true
            binding.webview.settings.useWideViewPort = true
            binding.webview.settings.domStorageEnabled = true
            binding.webview.webViewClient = Client(
                updateDate,
                paymentCode,
                referenceId,
                total
            )
        }
    }

    inner class Client(
        private val updateDate: String?,
        private val paymentCode: String?,
        private val referenceId: String?,
        val total: Long
    ) : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            if (request?.url.toString() == "success") {
                finish()
                val intent = Intent(this@CheckOutWebActivity, SuccessActivity::class.java)
                intent.putExtra("UPDATED_DATE", updateDate)
                intent.putExtra("PAYMENT_CODE", paymentCode)
                intent.putExtra("REFERENCE_ID", referenceId)
                intent.putExtra("TOTAL", total)
                startActivity(intent)
            } else {

            }

            return false
        }
    }
}