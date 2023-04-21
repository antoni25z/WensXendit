package com.wensolution.xendit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.wensolution.wensxendit.AvailableBankModel;
import com.wensolution.wensxendit.Test;
import com.wensolution.wensxendit.WensXendit;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WensXendit wensXendit = new WensXendit(this);
        wensXendit.setXenditApiKey("xnd_development_qBc22suMihFm1GdrX2x4yGOEhJoxjlFpEVVM6pwNPCHXsRs4w1T71VV6fUYw");
        wensXendit.setIlumaApiKey("iluma_development_KI7rvxgn7hll2NH65NIFQDTeyaQvtcIjLeuzZkNL3HFBuHi3TyqjjvbEuuuDj4j");

        wensXendit.getAvailableBanks(new Function1<List<AvailableBankModel>, Unit>() {
            @Override
            public Unit invoke(List<AvailableBankModel> availableBankModels) {
                Log.d("2504", String.valueOf(availableBankModels.size()));
                return null;
            }
        });
    }
}