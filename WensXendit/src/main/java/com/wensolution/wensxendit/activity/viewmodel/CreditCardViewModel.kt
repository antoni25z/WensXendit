package com.wensolution.wensxendit.activity.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.wensolution.wensxendit.apiservice.ApiConfig
import com.wensolution.wensxendit.apiservice.requestbody.CardPayRequestBody
import com.wensolution.wensxendit.apiservice.requestbody.CardRequestBody
import com.wensolution.wensxendit.apiservice.response.PaymentRequestResponse
import kotlinx.coroutines.launch
import quicktype.CreditCardPaymentResponse

class CreditCardViewModel(val username: String, val context: Context) : ViewModel() {

    private val service = ApiConfig.getBaseApiService(username)

    val response = MutableLiveData<CreditCardPaymentResponse?>()
    val getResponse: LiveData<CreditCardPaymentResponse?> get() = response

    val requestResponse = MutableLiveData<PaymentRequestResponse?>()
    val getRequestResponse: LiveData<PaymentRequestResponse?> get() = requestResponse

    val loading = MutableLiveData(false)
    val getLoading: LiveData<Boolean> get() = loading

    fun createCardMethods(paymentRequestBody: CardRequestBody) {
        loading.value = true
        viewModelScope.launch {
            val c_response = service?.cardPaymentMethods(paymentRequestBody)
            if (c_response?.isSuccessful == true) {
                response.postValue(c_response.body())
            } else {
                Toast.makeText(context, "Verifikasi Kartu Kredit Gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun createCardPayment(paymentRequestBody: CardPayRequestBody) {
        viewModelScope.launch {
            val c_response = service?.cardPaymentRequest(paymentRequestBody)
            loading.value = false
            requestResponse.postValue(c_response)
        }
    }
}