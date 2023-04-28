package com.wensolution.xendit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wensolution.wensxendit.AvailableBankModel;
import com.wensolution.wensxendit.PaymentMethod;
import com.wensolution.wensxendit.Test;
import com.wensolution.wensxendit.WensXendit;
import com.wensolution.wensxendit.apiservice.requestbody.ValidateNameRequestBody;
import com.wensolution.wensxendit.payout.Result;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WensXendit wensXendit = new WensXendit(this);
        wensXendit.setActiveMethods(new String[] {PaymentMethod.BNI, PaymentMethod.QRIS, PaymentMethod.ASTRAPAY,PaymentMethod.LINKAJA, PaymentMethod.OVO, PaymentMethod.SHOPEEPAY});
        wensXendit.setXenditApiKey("xnd_production_N4Yb6C3fREyFVOFr0SnDroCnnzPnrlR4O35M2tdaxA0r3CXUZ5QE5j6WGNXOp");
        wensXendit.setIlumaApiKey("iluma_live_eWwjuN0pD2rlJwDvoHrrV5NvC3p43OFhpP1Xsv5PZaApRHNv1wFSu91n4VjzRa8");

        findViewById(R.id.t).setOnClickListener(view -> wensXendit.startPayment(5000, "dwadwadadwa", "antoni"));


    }
}