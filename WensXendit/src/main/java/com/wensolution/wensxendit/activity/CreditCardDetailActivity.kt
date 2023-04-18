package com.wensolution.wensxendit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.wensolution.wensxendit.IntentKey
import com.wensolution.wensxendit.activity.viewmodel.CreditCardViewModel
import com.wensolution.wensxendit.ProgressDialog
import com.wensolution.wensxendit.activity.viewmodel.ViewModelFactory
import com.wensolution.wensxendit.apiservice.requestbody.CardPayRequestBody
import com.wensolution.wensxendit.apiservice.requestbody.CardRequestBody
import com.wensolution.wensxendit.databinding.ActivityCreditCardDetailBinding

class CreditCardDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreditCardDetailBinding
    lateinit var viewModel: CreditCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreditCardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val total = intent.getLongExtra("TOTAL_PAY", 0)
        val username = intent.getStringExtra("USERNAME") ?: ""
        val referenceId = intent.getStringExtra(IntentKey.REFERENCE_ID) ?: ""

        val progressDialog = ProgressDialog(this)

        val factory = ViewModelFactory(username, this)
        viewModel = ViewModelProvider(this, factory)[CreditCardViewModel::class.java]

        binding.totalTxt.text = total.toString()

        viewModel.getLoading.observe(this) {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        }

        viewModel.getResponse.observe(this) {
            if (it != null) {
                val requestBody = CardPayRequestBody(amount = total, paymentMethodID = it.id)
                viewModel.createCardPayment(requestBody)
            }
        }

        viewModel.getRequestResponse.observe(this) {
            if (it != null) {
                val intent = Intent(this, CreditCardVerificationActivity::class.java)
                intent.putExtra("URL", it.actions[0].url)
                startActivity(intent)
            }
        }

        binding.nextBtn.setOnClickListener {
            val cardNumber = binding.cardNumberEdt.text.toString()
            val month = binding.monthEdt.text.toString()
            val year = binding.yearEdt.text.toString()
            val cvv = binding.cvvEdt.text.toString()
            val holderName = binding.cardholderNameEdt.text.toString()

            val information = CardRequestBody.Card.CardInformation(
                cardNumber = cardNumber,
                expiryMonth = month,
                expiryYear = year,
                cvv = cvv,
                cardholderName = holderName
            )
            val card = CardRequestBody.Card(cardInformation = information)
            val requestBody = CardRequestBody(card = card, referenceId = referenceId)

            if (cardNumber.isNotBlank() && month.isNotBlank() && year.isNotBlank() && cvv.isNotBlank() && holderName.isNotBlank()) {
                viewModel.createCardMethods(requestBody)
            }
        }
    }
}