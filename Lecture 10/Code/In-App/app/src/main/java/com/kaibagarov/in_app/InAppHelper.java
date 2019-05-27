package com.kaibagarov.in_app;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.kaibagarov.in_app.inApp.IabHelper;
import com.kaibagarov.in_app.inApp.IabResult;

public class InAppHelper {

    private static String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3vt0h+Dcc2YaBGPR8nZL95FtNyd8sIArO5OkPqb4ptxCx4S9v8O0CgaTARInnmKFZkNFc0Sn2b/9wVM7sHFYkBuDim9GzLU8Jl8fEPFet622nwM/QCBG4lc0Dl9pF4c9ciDt5tlgfqn/j+MyqoIgABBQiVoRiFBzDo3KuQoMOloMuy84AVHwbZa/3zMYePYIWC3RVo8TD5dDeEpJaS7g3knFfwFc9NRB2wekEchFqwpHEh5Z/1jp5BQlSP7QxNPuzWcWnuhBgIOqVsupgxbgmVZebQ5tqPCCGDyd9ua7IH8u2AXezrwLsGmNlJF4C+1fJxhPTOaCS5t/QIeHEEgJBwIDAQAB";
    private static String productID = "test.inapp3";
    static final int RC_REQUEST = 10001;

    IabHelper mHelper;
    Context context;

    public InAppHelper(Context context) {
        this.context = context;
        mHelper = new IabHelper(this.context, base64EncodedPublicKey);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {

                if (!result.isSuccess()) {
                    Log.d("", "Setup finished with error");
                    return;
                }

                Log.d("", "Setup finished");
            }
        });
    }

    public void purchaseRemoveADS(IabHelper.OnIabPurchaseFinishedListener listener) {
        try {
            String payload = "";

            mHelper.launchPurchaseFlow((Activity)this.context, productID, RC_REQUEST,
                    listener, payload);
        } catch (Exception e) {
            Log.v("",e.getLocalizedMessage());
        }
    }
}
