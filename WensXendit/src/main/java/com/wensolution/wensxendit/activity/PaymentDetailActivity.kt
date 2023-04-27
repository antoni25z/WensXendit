package com.wensolution.wensxendit.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wensolution.wensxendit.*
import com.wensolution.wensxendit.databinding.ActivityPaymentDetailBinding
import java.util.concurrent.TimeUnit


class PaymentDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityPaymentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val va = intent.getStringExtra("VIRTUAL_ACCOUNT_NUMBER")
        val paymentCode = intent.getStringExtra("PAYMENT_CODE")
        val expired = intent.getStringExtra("EXPIRED")
        val total = intent.getLongExtra("TOTAL", 0)

        binding.vaTxt.text = va
        binding.totalTxt.text = total.toString()

        object : CountDownTimer(86400000, 1000) {
            override fun onTick(millisUntilFinish: Long) {
                val hour = TimeUnit.MILLISECONDS.toHours(millisUntilFinish)
                val minute = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinish) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinish))
                val second = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinish) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinish))

                val format = String.format("%02d:%02d:%02d",hour, minute, second)
                binding.expiredTxt.text = format
            }

            override fun onFinish() {

            }

        }.start()

        binding.expiredTxt.text = convertServerDateToUserTimeZone(expired)

        binding.panduanRv.layoutManager = LinearLayoutManager(this)

        when(paymentCode) {
            PaymentMethod.BCA -> {
                binding.methodImg.setImageResource(R.drawable.bca)
                binding.methodTxt.text = "BCA Virtual Account"

                val panduan = Panduan(getString(R.string.atm_bca), "ATM BCA")
                val panduan1 = Panduan(getString(R.string.bca_mobile), "Mobile Banking BCA")
                val panduan2 = Panduan(getString(R.string.mybca), "MY BCA")

                val array = ArrayList<Panduan>()
                array.add(panduan)
                array.add(panduan1)
                array.add(panduan2)

                binding.panduanRv.adapter = PanduanAdapter(array)
            }
            PaymentMethod.BJB -> {
                binding.methodImg.setImageResource(R.drawable.bjb)
                binding.methodTxt.text = "BJB Virtual Account"

                val panduan = Panduan(getString(R.string.internet_banking_bjb), "Internet Bangking")
                val panduan1 = Panduan(getString(R.string.mobile_banking_bjb), "Mobile Banking BJB")

                val array = ArrayList<Panduan>()
                array.add(panduan)
                array.add(panduan1)

                binding.panduanRv.adapter = PanduanAdapter(array)
            }
            PaymentMethod.BNI -> {
                binding.methodImg.setImageResource(R.drawable.bni)
                binding.methodTxt.text = "BNI Virtual Account"

                val panduan = Panduan(getString(R.string.atm_bni), "ATM BNI")
                val panduan1 = Panduan(getString(R.string.mobile_banking_bni), "Mobile Banking BNI")

                val array = ArrayList<Panduan>()
                array.add(panduan)
                array.add(panduan1)

                binding.panduanRv.adapter = PanduanAdapter(array)
            }
            PaymentMethod.BRI -> {
                binding.methodImg.setImageResource(R.drawable.bri)
                binding.methodTxt.text = "BRI Virtual Account"

                val panduan = Panduan(getString(R.string.atm_bri), "ATM BRI")
                val panduan1 = Panduan(getString(R.string.mobile_banking_bri), "Mobile Banking BRI")
                val panduan2 = Panduan(getString(R.string.internet_banking_bri), "Internet Banking BRI")

                val array = ArrayList<Panduan>()
                array.add(panduan)
                array.add(panduan1)
                array.add(panduan2)

                binding.panduanRv.adapter = PanduanAdapter(array)
            }
            PaymentMethod.BSI -> {
                binding.methodImg.setImageResource(R.drawable.bsi)
                binding.methodTxt.text = "BSI Virtual Account"

                val panduan = Panduan(getString(R.string.atm_bsi), "ATM BSI")
                val panduan1 = Panduan(getString(R.string.mobile_banking_bsi), "Mobile Banking BSI")
                val panduan2 = Panduan(getString(R.string.internet_banking_bsi), "Internet Banking BSI")

                val array = ArrayList<Panduan>()
                array.add(panduan)
                array.add(panduan1)
                array.add(panduan2)

                binding.panduanRv.adapter = PanduanAdapter(array)
            }
            PaymentMethod.BSS -> {
                binding.methodImg.setImageResource(R.drawable.bss)
                binding.methodTxt.text = "BSS Virtual Account"

                val panduan = Panduan(getString(R.string.atm_bss), "ATM BSS")
                val panduan1 = Panduan(getString(R.string.mobile_banking_bss), "Mobile Banking BSS")
                val panduan2 = Panduan(getString(R.string.internet_banking_bss), "Internet Banking BSS")

                val array = ArrayList<Panduan>()
                array.add(panduan)
                array.add(panduan1)
                array.add(panduan2)

                binding.panduanRv.adapter = PanduanAdapter(array)
            }
            PaymentMethod.MANDIRI -> {
                binding.methodImg.setImageResource(R.drawable.mandiri)
                binding.methodTxt.text = "MANDIRI Virtual Account"

                val panduan = Panduan(getString(R.string.atm_mandiri), "ATM Mandiri")
                val panduan1 = Panduan(getString(R.string.m_banking_mandiri), "Mobile Banking Mandiri")

                val array = ArrayList<Panduan>()
                array.add(panduan)
                array.add(panduan1)

                binding.panduanRv.adapter = PanduanAdapter(array)
            }
            PaymentMethod.PERMATA -> {
                binding.methodImg.setImageResource(R.drawable.permata)
                binding.methodTxt.text = "PERMATA Virtual Account"

                val panduan = Panduan(getString(R.string.atm_permata), "ATM Permata Bank")
                val panduan1 = Panduan(getString(R.string.mobile_banking_permata), "Mobile Banking Permata")
                val panduan2 = Panduan(getString(R.string.internet_banking_permata), "Internet Banking Permata")

                val array = ArrayList<Panduan>()
                array.add(panduan)
                array.add(panduan1)
                array.add(panduan2)

                binding.panduanRv.adapter = PanduanAdapter(array)
            }
        }

        binding.copyBtn.setOnClickListener {
            val cm: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.setPrimaryClip(ClipData.newPlainText("text", binding.vaTxt.text.toString()))
            Toast.makeText(this, "Salin Berhasil", Toast.LENGTH_SHORT).show()
        }
    }
}