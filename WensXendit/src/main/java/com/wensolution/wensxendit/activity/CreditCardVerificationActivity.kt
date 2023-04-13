package com.wensolution.wensxendit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wensolution.wensxendit.databinding.ActivityCreditCardVerificationBinding

class CreditCardVerificationActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreditCardVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreditCardVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra("URL")?.let { binding.webview.loadUrl(it) }
    }
}