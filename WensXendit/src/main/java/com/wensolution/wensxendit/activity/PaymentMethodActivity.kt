package com.wensolution.wensxendit.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wensolution.wensxendit.*
import com.wensolution.wensxendit.activity.viewmodel.PaymentMethodViewModel
import com.wensolution.wensxendit.activity.viewmodel.ViewModelFactory
import com.wensolution.wensxendit.apiservice.requestbody.EwalletRequestBody
import com.wensolution.wensxendit.apiservice.requestbody.QRRequestBody
import com.wensolution.wensxendit.apiservice.requestbody.VaRequestBody
import com.wensolution.wensxendit.databinding.ActivityPaymentMethodBinding
import java.util.UUID

class PaymentMethodActivity : AppCompatActivity(), PaymentClick {

    lateinit var binding: ActivityPaymentMethodBinding
    lateinit var viewModel: PaymentMethodViewModel

    var totalPay = 0
    var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actives = intent.getStringArrayExtra(ACTIVE_METHOD)
        totalPay = intent.getIntExtra(TOTAL_PAY, 0)
        username = intent.getStringExtra(USERNAME) ?: ""

        val factory = ViewModelFactory(username, this)

        viewModel = ViewModelProvider(this, factory)[PaymentMethodViewModel::class.java]
        val progress = ProgressDialog(this)

        if (actives != null && totalPay != 0) {
            binding.ewalletRv.layoutManager = LinearLayoutManager(this)
            binding.vaRv.layoutManager = LinearLayoutManager(this)
            binding.qrRv.layoutManager = LinearLayoutManager(this)
            binding.cardRv.layoutManager = LinearLayoutManager(this)

            val ewallets = resources.getStringArray(R.array.ewallet_code)
            val vacode = resources.getStringArray(R.array.va_code)
            val qrs = resources.getStringArray(R.array.qris_code)
            val cards = resources.getStringArray(R.array.credit_card_code)

            binding.ewalletRv.adapter = PaymentMethodAdapter(ewallets, actives, totalPay, "EWALLET", this)
            binding.vaRv.adapter = PaymentMethodAdapter(vacode, actives, totalPay, "VA", this)
            binding.qrRv.adapter = PaymentMethodAdapter(qrs, actives, totalPay, "QR", this)
            binding.cardRv.adapter = PaymentMethodAdapter(cards, actives, totalPay, "CARD", this)
        }

        viewModel.getLoading.observe(this) {
            if (it) {
                progress.show()
            } else {
                progress.dismiss()
            }
        }

        viewModel.getResponse.observe(this) {
            if (it?.paymentMethod?.type == "EWALLET") {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(it.actions[0].url)
                startActivity(i)
            } else if (it?.paymentMethod?.type == "VIRTUAL_ACCOUNT") {
                val i = Intent(this, PaymentDetailActivity::class.java)
                i.putExtra("VIRTUAL_ACCOUNT_NUMBER", it.paymentMethod.virtualAccount?.channelProperties?.virtualAccountNumber)
                i.putExtra("PAYMENT_CODE", it.paymentMethod.virtualAccount?.channelCode)
                i.putExtra("EXPIRED", it.paymentMethod.virtualAccount?.channelProperties?.expiresAt)
                i.putExtra("TOTAL", it.amount)
                startActivity(i)
            } else if (it?.paymentMethod?.type == "QR_CODE") {
                val i = Intent(this, QRDetailActivity::class.java)
                i.putExtra("QR_STRING", it.paymentMethod.qrCode?.channelProperties?.qrString)
                i.putExtra("EXPIRED", it.paymentMethod.qrCode?.channelProperties?.expiresAt)
                i.putExtra("TOTAL", it.amount)
                startActivity(i)
            }
        }
    }

    companion object {
        const val ACTIVE_METHOD = "ACTIVE_METHOD"
        const val TOTAL_PAY = "TOTAL_PAY"
        const val USERNAME = "USERNAME"
    }

    override fun onClick(payment: String, mode: String, subTotal: Double) {
        if (mode == "EWALLET") {
            val property = EwalletRequestBody.ChannelProperty("")
            val ewallet = EwalletRequestBody.Ewallet(payment, property)
            val paymentMethod = EwalletRequestBody.PaymentMethod(ewallet = ewallet)
            val requestBody = EwalletRequestBody(amount = subTotal.toInt(), paymentMethod = paymentMethod)
            viewModel.createPayment(requestBody)
        } else if (mode == "VA") {
            val property = VaRequestBody.ChannelProperty("")
            val va = VaRequestBody.VirtualAccount(payment, property)
            val paymentMethod = VaRequestBody.PaymentMethod(virtualAccount = va, referenceId = "pm-level-${UUID.randomUUID()}")
            val requestBody = VaRequestBody(amount = subTotal.toInt(), paymentMethod = paymentMethod)
            viewModel.createVaPayment(requestBody)
        } else if (mode == "QR") {
            val requestBody = QRRequestBody(amount = subTotal.toInt())
            viewModel.createQrPayment(requestBody)
        } else if (mode == "CARD") {
            val intent = Intent(this, CreditCardDetailActivity::class.java)
            intent.putExtra(TOTAL_PAY, totalPay)
            intent.putExtra(USERNAME, username)
            startActivity(intent)
        }
    }
}