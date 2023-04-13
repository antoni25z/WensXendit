package com.wensolution.wensxendit.payout

import android.content.Context
import android.util.Log
import com.wensolution.wensxendit.apiservice.ApiConfig
import com.wensolution.wensxendit.apiservice.requestbody.DisbursmentRequestBody
import com.wensolution.wensxendit.apiservice.response.AvailableBankResponse
import com.wensolution.wensxendit.apiservice.response.DisbursmentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Payout {

    fun createDisbursement(username: String, amount: Long, bankCode: String, holderName: String, accountNumber: String, disburse: (message: String) -> Unit) {
        val service = ApiConfig.getBaseApiService(username)
        val externalId = "disb-${System.currentTimeMillis()}"
        val describe = "Penarikan Saldo"

        val disbursmentRequestBody = DisbursmentRequestBody(externalId, amount, bankCode, holderName, accountNumber, describe)
        service?.createDisbursements(disbursmentRequestBody)?.enqueue(object : Callback<DisbursmentResponse> {
            override fun onResponse(
                call: Call<DisbursmentResponse>,
                response: Response<DisbursmentResponse>
            ) {
                if (response.isSuccessful) {
                    disburse("Penarikan Sedang Diproses")
                } else {
                    if ( response.code() == 400) {
                        disburse("Rekening tidak ditemukan")
                    }
                }
            }

            override fun onFailure(call: Call<DisbursmentResponse>, t: Throwable) {
                Log.d("de", t.message, t)
            }

        })
    }

    fun getAvailableBanks(username: String, banks: (banks: List<AvailableBankResponse>) -> Unit) {
        val service = ApiConfig.getBaseApiService(username)
        service?.getAvailableBanks()?.enqueue(object : Callback<List<AvailableBankResponse>> {
            override fun onResponse(
                call: Call<List<AvailableBankResponse>>,
                response: Response<List<AvailableBankResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { banks(it) }
                }
            }

            override fun onFailure(call: Call<List<AvailableBankResponse>>, t: Throwable) {
                Log.d("de", t.message, t)
            }

        })
    }

}