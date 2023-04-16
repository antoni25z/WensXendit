package com.wensolution.wensxendit

import android.app.Dialog
import android.content.Context

class ProgressDialog(context: Context) : Dialog(context) {
    init {
        setContentView(R.layout.progress_layout)
    }
}