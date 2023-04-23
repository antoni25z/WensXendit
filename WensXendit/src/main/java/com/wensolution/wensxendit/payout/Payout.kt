package com.wensolution.wensxendit.payout

import android.util.Log
import com.google.gson.JsonElement
import com.wensolution.wensxendit.apiservice.ApiConfig
import com.wensolution.wensxendit.apiservice.requestbody.DisbursmentRequestBody
import com.wensolution.wensxendit.apiservice.requestbody.ValidateNameRequestBody
import com.wensolution.wensxendit.apiservice.response.DisbursmentResponse
import com.wensolution.wensxendit.apiservice.response.FetchDisburtsmentResponse
import com.wensolution.wensxendit.apiservice.response.IlumaAvailableBankResponse
import com.wensolution.wensxendit.apiservice.response.ValidateNameResponse
import com.wensolution.wensxendit.apiservice.response.XenditAvailableBankResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Payout() {

    fun createDisbursement(
        username: String,
        externalId: String,
        amount: Long,
        bankCode: String,
        holderName: String,
        accountNumber: String,
        disburse: (message: String, success: Boolean) -> Unit
    ) {
        val service = ApiConfig.getXenditApiService(username)
        val describe = "Penarikan Saldo"

        val disbursmentRequestBody = DisbursmentRequestBody(
            externalId,
            amount,
            bankCode,
            holderName,
            accountNumber,
            describe
        )
        service?.createDisbursements(disbursmentRequestBody)
            ?.enqueue(object : Callback<DisbursmentResponse> {
                override fun onResponse(
                    call: Call<DisbursmentResponse>,
                    response: Response<DisbursmentResponse>
                ) {
                    if (response.isSuccessful) {
                        disburse("Penarikan Sedang Diproses", true)
                    } else {
                        disburse("Terjadi Kesalahan", false)
                    }
                }

                override fun onFailure(call: Call<DisbursmentResponse>, t: Throwable) {
                    Log.d("de", t.message, t)
                    disburse("Terjadi Kesalahan", false)
                }

            })
    }

    fun getIlumaAvailableBanks(
        username: String,
        banks: (banks: List<IlumaAvailableBankResponse>) -> Unit
    ) {
        val service = ApiConfig.getIlumaApiService(username)
        service?.getIlumaAvailableBanks()
            ?.enqueue(object : Callback<List<IlumaAvailableBankResponse>> {

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

    fun getXenditAvailableBanks(
        username: String,
        banks: (banks: List<XenditAvailableBankResponse>) -> Unit
    ) {
        val service = ApiConfig.getXenditApiService(username)
        service?.getXenditAvailableBanks()
            ?.enqueue(object : Callback<List<XenditAvailableBankResponse>> {

                override fun onResponse(
                    call: Call<List<XenditAvailableBankResponse>>,
                    response: Response<List<XenditAvailableBankResponse>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { banks(it) }
                    }
                }

                override fun onFailure(
                    call: Call<List<XenditAvailableBankResponse>>,
                    t: Throwable
                ) {
                }

            })
    }

    fun validateBankName(
        username: String,
        data: ValidateNameRequestBody,
        result: (result: Result) -> Unit
    ) {
        val service = ApiConfig.getIlumaApiService(username)
        service?.validateBankName(data)?.enqueue(object : Callback<ValidateNameResponse> {
            override fun onResponse(
                call: Call<ValidateNameResponse>,
                response: Response<ValidateNameResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status == "COMPLETED") {
                        if (response.body()?.result?.isFound == true) {
                            result(
                                Result(
                                    found = response.body()!!.result.isFound,
                                    message = "Rekening ditemukan",
                                    holderName = response.body()!!.result.accountHolderName,
                                    bankName = response.body()!!.bankCode,
                                    bankNumber = response.body()!!.bankAccountNumber
                                )
                            )
                        } else {
                            result(
                                Result(
                                    found = response.body()!!.result.isFound,
                                    message = "Rekening tidak ditemukan",
                                    holderName = "",
                                    bankName = "",
                                    bankNumber = ""
                                )
                            )
                        }
                    } else {
                        result(
                            Result(
                                found = false,
                                message = "Rekening tidak ditemukan",
                                holderName = "",
                                bankName = "",
                                bankNumber = ""
                            )
                        )
                    }
                } else {
                    result(
                        Result(
                            found = false,
                            message = "Rekening tidak ditemukan-",
                            holderName = "",
                            bankName = "",
                            bankNumber = ""
                        )
                    )
                }
            }

            override fun onFailure(call: Call<ValidateNameResponse>, t: Throwable) {
                result(
                    Result(
                        found = false,
                        message = "Rekening tidak ditemukan--",
                        holderName = "",
                        bankName = "",
                        bankNumber = ""
                    )
                )
            }

        })
    }

    fun getDisbursement(externalId: String, username: String, disbursement: (disburse: FetchDisburtsmentResponse) -> Unit) {
        val service = ApiConfig.getIlumaApiService(username)
        service?.getDisbursement(externalId)?.enqueue(object : Callback<FetchDisburtsmentResponse> {
            override fun onResponse(
                call: Call<FetchDisburtsmentResponse>,
                response: Response<FetchDisburtsmentResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { disbursement(it) }
                }
            }

            override fun onFailure(call: Call<FetchDisburtsmentResponse>, t: Throwable) {

            }

        })
    }

}
