package com.wensolution.wensxendit.payout

import android.util.Log
import com.wensolution.wensxendit.apiservice.ApiConfig
import com.wensolution.wensxendit.apiservice.requestbody.DisbursmentRequestBody
import com.wensolution.wensxendit.apiservice.response.DisbursmentResponse
import com.wensolution.wensxendit.apiservice.response.IlumaAvailableBankResponse
import com.wensolution.wensxendit.apiservice.response.XenditAvailableBankResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Payout() {

    fun createDisbursement( username: String, externalId: String, amount: Long, bankCode: String, holderName: String, accountNumber: String, disburse: (message: String) -> Unit) {
        val service = ApiConfig.getXenditApiService(username)
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
                    disburse("Terjadi Kesalahan")
                }
            }

            override fun onFailure(call: Call<DisbursmentResponse>, t: Throwable) {
                Log.d("de", t.message, t)
            }

        })
    }

    fun getIlumaAvailableBanks(username:String, banks: (banks: List<IlumaAvailableBankResponse>) -> Unit) {
        val service = ApiConfig.getIlumaApiService(username)
        service?.getIlumaAvailableBanks()?.enqueue(object : Callback<List<IlumaAvailableBankResponse>> {

            override fun onResponse(
                call: Call<List<IlumaAvailableBankResponse>>,
                response: Response<List<IlumaAvailableBankResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { banks(it) }
                }
            }

            override fun onFailure(call: Call<List<IlumaAvailableBankResponse>>, t: Throwable) {
            }

        })
    }

    fun getXenditAvailableBanks(username:String, banks: (banks: List<XenditAvailableBankResponse>) -> Unit) {
        val service = ApiConfig.getXenditApiService(username)
        service?.getXenditAvailableBanks()?.enqueue(object : Callback<List<XenditAvailableBankResponse>> {

            override fun onResponse(
                call: Call<List<XenditAvailableBankResponse>>,
                response: Response<List<XenditAvailableBankResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { banks(it) }
                }
            }

            override fun onFailure(call: Call<List<XenditAvailableBankResponse>>, t: Throwable) {
            }

        })
    }

}
