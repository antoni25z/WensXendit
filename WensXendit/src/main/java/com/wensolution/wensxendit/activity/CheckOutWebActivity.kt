package com.wensolution.wensxendit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.wensolution.wensxendit.IntentKey
import com.wensolution.wensxendit.databinding.ActivityCheckOutWebBinding

class CheckOutWebActivity : AppCompatActivity() {

    lateinit var binding: ActivityCheckOutWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckOutWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = intent.getStringExtra(IntentKey.URL_KEY)
        if (url != null) {
            binding.webview.loadUrl(url)
            binding.webview.settings.javaScriptEnabled = true
            binding.webview.settings.loadsImagesAutomatically = true
            binding.webview.settings.useWideViewPort = true
            binding.webview.settings.domStorageEnabled = true
            binding.webview.webViewClient = Client()
        }
    }

    inner class Client: WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {

            return false
        }
    }
}