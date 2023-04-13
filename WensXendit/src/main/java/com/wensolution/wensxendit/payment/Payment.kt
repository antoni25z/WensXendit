package com.wensolution.wensxendit.payment

import android.content.Context
import android.content.Intent
import com.wensolution.wensxendit.activity.PaymentMethodActivity

class Payment(val context: Context) {
    fun startPayment(activeMethods: Array<String>, amount: Long, username: String) {
        val intent = Intent(context, PaymentMethodActivity::class.java)
        intent.putExtra(ACTIVE_METHOD, activeMethods)
        intent.putExtra(TOTAL_PAY, amount)
        intent.putExtra(USERNAME, username)
        context.startActivity(intent)
    }

    companion object {
        private const val ACTIVE_METHOD = "ACTIVE_METHOD"
        private const val TOTAL_PAY = "TOTAL_PAY"
        private const val USERNAME = "USERNAME"
    }
}