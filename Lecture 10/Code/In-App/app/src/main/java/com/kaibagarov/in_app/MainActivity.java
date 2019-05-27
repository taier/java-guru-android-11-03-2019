package com.kaibagarov.in_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kaibagarov.in_app.inApp.IabHelper;
import com.kaibagarov.in_app.inApp.IabResult;
import com.kaibagarov.in_app.inApp.Purchase;

public class MainActivity extends AppCompatActivity implements IabHelper.OnIabPurchaseFinishedListener {

    InAppHelper inAppHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inAppHelper = new InAppHelper(this);
    }

    public void onRemoveAdsClick(View view) {
        inAppHelper.purchaseRemoveADS(this);
    }

    @Override
    public void onIabPurchaseFinished(IabResult result, Purchase info) {
        Log.v("",result.getMessage());
    }
}
