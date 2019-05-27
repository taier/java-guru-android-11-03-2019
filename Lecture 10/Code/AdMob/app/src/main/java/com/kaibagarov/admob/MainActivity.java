package com.kaibagarov.admob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.kaibagarov.android_helpers.DKAdHelper;

public class MainActivity extends AppCompatActivity {

    DKAdHelper adHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout adContainer = findViewById(R.id.adContainer);
        adHelper = new DKAdHelper(this,"ca-app-pub-XXXXX"); // Publisher ID
        adHelper.bannerAdUnitID = "ca-app-pub-XXXX"; // Banner ID
        adHelper.containerForAD = adContainer;

        if (BuildConfig.DEBUG) {
            adHelper.showTestAdBanner();
        } else {
            adHelper.showAdBanner();
        }
    }
}
