package com.wensolution.wensxendit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wensolution.wensxendit.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {

    lateinit var binding: ActivitySuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val updateDate = intent.getStringExtra(IntentKey.UPDATED_DATE)
        val paymentCode = intent.getStringExtra(IntentKey.PAYMENT_CODE)
        val referenceId = intent.getStringExtra(IntentKey.REFERENCE_ID)
        val total = intent.getLongExtra(IntentKey.TOTAL, 0)

        binding.amountTxt.text = convertCurrencyFormat( total.toDouble())
        binding.amount2Txt.text = convertCurrencyFormat( total.toDouble())
        binding.paymentMethodTxt.text = paymentCode
        binding.referenceIdTxt.text = referenceId
        binding.timeTxt.text = convertServerDateToUserTimeZone(updateDate)

        binding.doneBtn.setOnClickListener {
            finish()
        }
    }
}