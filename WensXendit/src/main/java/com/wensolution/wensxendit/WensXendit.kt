package com.wensolution.wensxendit

import android.content.Context
import com.wensolution.wensxendit.payment.Payment
import com.wensolution.wensxendit.payout.Payout

class WensXendit(val context: Context) {
    private var xenditApiKey = ""

    private var ilumaApiKey = ""
    private var activeMethods: Array<String> = emptyArray()

    private val payment = Payment(context)
    private val payout = Payout()

    fun setActiveMethods(activeMethods: Array<String>) {
        this.activeMethods = activeMethods
    }
    fun setXenditApiKey(xenditApiKey: String) {
        this.xenditApiKey = xenditApiKey
    }

    fun setIlumaApiKey(ilumaApiKey: String) {
        this.ilumaApiKey = ilumaApiKey
    }

    fun startPayment(amount: Long, referenceId: String, customerName: String) {
        if (xenditApiKey.isNotBlank() && activeMethods.isNotEmpty()) {
            payment.startPayment(activeMethods, amount, xenditApiKey, referenceId, customerName)
        }
    }

    fun createDisbursement(externalId:String, amount: Long, bankCode: String, holderName: String, accountNumber: String, disburse: (message: String) -> Unit) {
        payout.createDisbursement(xenditApiKey, externalId, amount, bankCode, holderName, accountNumber, disburse)
    }

    fun getAvailableBanks(banks: (banks: List<AvailableBankModel>) -> Unit) {
        val newList = ArrayList<AvailableBankModel>()
        payout.getIlumaAvailableBanks(ilumaApiKey) { ilumas ->
            payout.getXenditAvailableBanks(xenditApiKey) { xendits ->
                for (i in ilumas) {
                    for (x in xendits) {
                        if (i.name == x.name) {
                            if (x.canDisburse) {
                                val availableBankModel = AvailableBankModel(
                                    x.name,
                                    i.code,
                                    x.code
                                )
                                newList.add(availableBankModel)
                            }
                        }
                    }
                }
                banks(newList)
            }
        }
    }


}