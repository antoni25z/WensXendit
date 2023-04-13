package com.wensolution.wensxendit.activity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wensolution.wensxendit.apiservice.ApiConfig
import com.wensolution.wensxendit.apiservice.requestbody.CardRequestBody
import com.wensolution.wensxendit.apiservice.requestbody.EwalletRequestBody
import com.wensolution.wensxendit.apiservice.requestbody.QRRequestBody
import com.wensolution.wensxendit.apiservice.requestbody.VaRequestBody
import com.wensolution.wensxendit.apiservice.response.PaymentRequestResponse
import kotlinx.coroutines.launch

class PaymentMethodViewModel(username: String) : ViewModel() {

    private val service = ApiConfig.getBaseApiService(username)

    val response = MutableLiveData<PaymentRequestResponse?>()
    val getResponse: LiveData<PaymentRequestResponse?> get() = response

    val loading = MutableLiveData(false)
    val getLoading: LiveData<Boolean> get() = loading

    fun createPayment(paymentRequestBody: EwalletRequestBody) {
        loading.value = true
        viewModelScope.launch {
            val c_response = service?.ewalletPaymentRequest(paymentRequestBody)
            loading.value = false
            response.postValue(c_response)
        }
    }

    fun createVaPayment(paymentRequestBody: VaRequestBody) {
        loading.value = true
        viewModelScope.launch {
            val c_response = service?.vaPaymentRequest(paymentRequestBody)
            loading.value = false
            response.postValue(c_response)
        }
    }

    fun createQrPayment(paymentRequestBody: QRRequestBody) {
        loading.value = true
        viewModelScope.launch {
            val c_response = service?.qrPaymentRequest(paymentRequestBody)
            loading.value = false
            response.postValue(c_response)
        }
    }

}