package com.wensolution.wensxendit.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wensolution.wensxendit.*
import com.wensolution.wensxendit.IntentKey.CUSTOMER_NAME
import com.wensolution.wensxendit.IntentKey.REFERENCE_ID
import com.wensolution.wensxendit.activity.viewmodel.PaymentMethodViewModel
import com.wensolution.wensxendit.activity.viewmodel.ViewModelFactory
import com.wensolution.wensxendit.apiservice.requestbody.EwalletRequestBody
import com.wensolution.wensxendit.apiservice.requestbody.QRRequestBody
import com.wensolution.wensxendit.apiservice.requestbody.VaRequestBody
import com.wensolution.wensxendit.databinding.ActivityPaymentMethodBinding

class PaymentMethodActivity : AppCompatActivity(), PaymentClick, OvoClick {

    private lateinit var binding: ActivityPaymentMethodBinding
    private lateinit var viewModel: PaymentMethodViewModel

    private var totalPay = 0L
    private var username = ""
    private var referenceId = ""
    private var customerName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actives = intent.getStringArrayExtra(ACTIVE_METHOD)
        totalPay = intent.getLongExtra(TOTAL_PAY, 0)
        username = intent.getStringExtra(USERNAME) ?: ""
        referenceId = intent.getStringExtra(REFERENCE_ID) ?: ""
        customerName = intent.getStringExtra(CUSTOMER_NAME) ?: ""

        val factory = ViewModelFactory(username, this)

        viewModel = ViewModelProvider(this, factory)[PaymentMethodViewModel::class.java]
        val progress = ProgressDialog(this)

        val ewalletList = ArrayList<String>()
        val vaList = ArrayList<String>()
        val qrList = ArrayList<String>()
        val cardList = ArrayList<String>()

        if (actives != null && totalPay != 0L) {
            binding.ewalletRv.layoutManager = LinearLayoutManager(this)
            binding.vaRv.layoutManager = LinearLayoutManager(this)
            binding.qrRv.layoutManager = LinearLayoutManager(this)
            binding.cardRv.layoutManager = LinearLayoutManager(this)
            binding.ewalletRv.isNestedScrollingEnabled = false
            binding.vaRv.isNestedScrollingEnabled = false
            binding.qrRv.isNestedScrollingEnabled = false
            binding.cardRv.isNestedScrollingEnabled = false

            val ewallets = resources.getStringArray(R.array.ewallet_code)
            val vacode = resources.getStringArray(R.array.va_code)
            val qrs = resources.getStringArray(R.array.qris_code)
            val cards = resources.getStringArray(R.array.credit_card_code)

            for (i in vacode) {
                if(actives.contains(i)) {
                    vaList.add(i)
                }
            }

            for (i in qrs) {
                if(actives.contains(i)) {
                    qrList.add(i)
                }
            }

            for (i in ewallets) {
                if(actives.contains(i)) {
                    ewalletList.add(i)
                }
            }

            for (i in cards) {
                if(actives.contains(i)) {
                    cardList.add(i)
                }
            }

            if (vaList.isEmpty()) {
                binding.vaCard.isVisible = false
            }
            if (ewalletList.isEmpty()) {
                binding.ewalletCard.isVisible = false
            }
            if (qrList.isEmpty()) {
                binding.qrCard.isVisible = false
            }
            if (cardList.isEmpty()) {
                binding.cardCard.isVisible = false
            }

            binding.ewalletRv.adapter = PaymentMethodAdapter(ewalletList, totalPay, "EWALLET", this, this)
            binding.vaRv.adapter = PaymentMethodAdapter(vaList, totalPay, "VA", this, this)
            binding.qrRv.adapter = PaymentMethodAdapter(qrList, totalPay, "QR", this, this)
            binding.cardRv.adapter = PaymentMethodAdapter(cardList,  totalPay, "CARD", this, this)
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
                if (it.paymentMethod.ewallet?.channelCode == PaymentMethod.SHOPEEPAY) {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(it.actions[0].url)
                    startActivity(i)
                } else if (it.paymentMethod.ewallet?.channelCode == PaymentMethod.OVO) {
                    return@observe
                }else {
                    val intent = Intent(this, CheckOutWebActivity::class.java)
                    intent.putExtra(IntentKey.URL_KEY, it.actions[0].url)
                    startActivity(intent)
                }
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
        when (mode) {
            "EWALLET" -> {
                val property = EwalletRequestBody.PaymentMethod.Ewallet.ChannelProperties("https://success", "https://failure")
                val ewallet = EwalletRequestBody.PaymentMethod.Ewallet(payment, property)
                val paymentMethod = EwalletRequestBody.PaymentMethod(ewallet = ewallet, referenceId = referenceId)
                val requestBody = EwalletRequestBody(amount = subTotal.toInt(), paymentMethod = paymentMethod)
                viewModel.createPayment(requestBody)
            }
            "VA" -> {
                val property = VaRequestBody.PaymentMethod.VirtualAccount.ChannelProperty(customerName)
                val va = VaRequestBody.PaymentMethod.VirtualAccount(payment, property)
                val paymentMethod = VaRequestBody.PaymentMethod(virtualAccount = va, referenceId = referenceId)
                val requestBody = VaRequestBody(amount = subTotal.toInt(), paymentMethod = paymentMethod)
                viewModel.createVaPayment(requestBody)
            }
            "QR" -> {
                val paymentMethod = QRRequestBody.PaymentMethod(referenceId = referenceId)
                val requestBody = QRRequestBody(amount = subTotal.toInt(), paymentMethod = paymentMethod)
                viewModel.createQrPayment(requestBody)
            }
            "CARD" -> {
                val intent = Intent(this, CreditCardDetailActivity::class.java)
                intent.putExtra(TOTAL_PAY, totalPay)
                intent.putExtra(USERNAME, username)
                startActivity(intent)
            }
        }
    }

    override fun onOvoClick(payment: String, subtotal: Double, phoneNumber: String) {
        val property = EwalletRequestBody.PaymentMethod.Ewallet.ChannelProperties("https://success", "https://failure", "+62" + phoneNumber)
        val ewallet = EwalletRequestBody.PaymentMethod.Ewallet(payment, property)
        val paymentMethod = EwalletRequestBody.PaymentMethod(ewallet = ewallet, referenceId = referenceId)
        val requestBody = EwalletRequestBody(amount = subtotal.toInt(), paymentMethod = paymentMethod)
        viewModel.createOvoPayment(requestBody)
    }
}