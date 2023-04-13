package com.wensolution.wensxendit

interface PaymentClick {
    fun onClick(payment: String, mode: String, subTotal: Double)
}