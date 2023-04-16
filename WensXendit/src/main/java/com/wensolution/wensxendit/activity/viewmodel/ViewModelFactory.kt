package com.wensolution.wensxendit.activity.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(val username: String, val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PaymentMethodViewModel::class.java)) {
            PaymentMethodViewModel(username) as T
        } else {
            CreditCardViewModel(username, context) as T
        }
    }
}