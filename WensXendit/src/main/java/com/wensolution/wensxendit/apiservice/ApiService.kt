package com.wensolution.wensxendit.apiservice

import com.wensolution.wensxendit.apiservice.requestbody.*
import com.wensolution.wensxendit.apiservice.response.XenditAvailableBankResponse
import com.wensolution.wensxendit.apiservice.response.DisbursmentResponse
import com.wensolution.wensxendit.apiservice.response.IlumaAvailableBankResponse
import com.wensolution.wensxendit.apiservice.response.PaymentRequestResponse
import quicktype.CreditCardPaymentResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("payment_requests")
    suspend fun ewalletPaymentRequest(@Body paymentRequestBody: EwalletRequestBody): PaymentRequestResponse

    @POST("payment_requests")
    suspend fun vaPaymentRequest(@Body paymentRequestBody: VaRequestBody): PaymentRequestResponse

    @POST("payment_requests")
    suspend fun qrPaymentRequest(@Body paymentRequestBody: QRRequestBody): PaymentRequestResponse

    @POST("payment_methods")
    suspend fun cardPaymentMethods(@Body paymentRequestBody: CardRequestBody): Response<CreditCardPaymentResponse>

    @POST("payment_requests")
    suspend fun cardPaymentRequest(@Body paymentRequestBody: CardPayRequestBody): PaymentRequestResponse

    @POST("disbursements")
    fun createDisbursements(@Body disbursmentRequestBody: DisbursmentRequestBody) : Call<DisbursmentResponse>

    @GET("available_disbursements_banks")
    fun getXenditAvailableBanks() : Call<List<XenditAvailableBankResponse>>

    @GET("bank/available_bank_codes")
    fun getIlumaAvailableBanks() : Call<List<IlumaAvailableBankResponse>>
}