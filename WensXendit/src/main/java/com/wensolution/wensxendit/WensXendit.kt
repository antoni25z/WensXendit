package com.wensolution.wensxendit

import android.content.Context
import com.wensolution.wensxendit.apiservice.response.AvailableBankResponse
import com.wensolution.wensxendit.payment.Payment
import com.wensolution.wensxendit.payout.Payout

class WensXendit(val context: Context) {
    var xenditApiKey = ""
    get() = field
    set(value) {field = value}

    var activeMethods: Array<String> = emptyArray()
        get() = field
        set(value) {field = value}

    private val payment = Payment(context)
    private val payout = Payout()

    fun startPayment(amount: Long) {
        if (xenditApiKey.isNotBlank() && activeMethods.isNotEmpty()) {
            payment.startPayment(activeMethods, amount, xenditApiKey)
        }
    }

    fun createDisbursement(amount: Long, bankCode: String, holderName: String, accountNumber: String, disburse: (message: String) -> Unit) {
        payout.createDisbursement(xenditApiKey, amount, bankCode, holderName, accountNumber, disburse)
    }

    fun getAvailableBanks(banks: (banks: List<AvailableBankResponse>) -> Unit) {
        payout.getAvailableBanks(xenditApiKey, banks)
    }


}