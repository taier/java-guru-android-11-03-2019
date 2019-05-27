package com.example.den.lesson10.DataSources.Local;

import android.content.Context;
import android.util.Log;

import com.yakivmospan.scytale.Crypto;
import com.yakivmospan.scytale.Options;
import com.yakivmospan.scytale.Store;

import javax.crypto.SecretKey;

public class SecureDataHelper {

    private static String ALIAS = "SOME_AWESOME_ALIAS";

    public static void saveSensitiveData(String data, String forKey, Context context) {
        Store store = new Store(context);
        if (!store.hasKey(ALIAS)) {
            store.generateSymmetricKey(ALIAS, null);
        }

        SecretKey key = store.getSymmetricKey(ALIAS, null);

        Crypto crypto = new Crypto(Options.TRANSFORMATION_SYMMETRIC);
        String encryptedData = crypto.encrypt(data, key);
        Log.i("Scytale", "Encrypted data: " + encryptedData);

        SharedPreferencesHelper.saveStringFroKey(encryptedData, forKey, context);
    }

    public static String getSensitiveData(String forKey, Context context) {
        Store store = new Store(context);
        if (!store.hasKey(ALIAS)) {
            store.generateSymmetricKey(ALIAS, null);
        }

        SecretKey key = store.getSymmetricKey(ALIAS, null);
        Crypto crypto = new Crypto(Options.TRANSFORMATION_SYMMETRIC);

        String data = SharedPreferencesHelper.getStringForKey(forKey, context);
        if (data == null) {
            return null;
        }

        String decryptedData = crypto.decrypt(data, key);
        Log.i("Scytale", "Decrypted data: " + decryptedData);

        return decryptedData;

    }
}
