package com.wensolution.wensxendit.payout

data class Result(
    val found: Boolean,
    val message: String,
    val holderName: String,
    val bankNumber: String,
    val bankName: String
)
