package com.wensolution.wensxendit

import android.app.Dialog
import android.content.Context

class ProgressDialog(val context: Context) : Dialog(context) {
    init {
        setContentView(R.layout.progress_layout)
    }
}