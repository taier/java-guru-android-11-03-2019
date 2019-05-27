package com.example.den.lesson10.DataSources.Local;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesHelper {

    private static String MAIN_PREFERENCES_KEY = "MAIN_PREFERENCES_KEY";

    public static void saveFavorite(String itemID, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MAIN_PREFERENCES_KEY , MODE_PRIVATE).edit();
        editor.putBoolean(itemID, true);
        editor.apply();
    }

    public static void removeFromFavorite(String itemID, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MAIN_PREFERENCES_KEY , MODE_PRIVATE).edit();
        editor.remove(itemID);
        editor.apply();
    }

    public static boolean isFavorited(String itemID, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(MAIN_PREFERENCES_KEY, MODE_PRIVATE);
        boolean isFavorited = prefs.getBoolean(itemID, false);

        return isFavorited;
    }

    public static void saveStringFroKey(String string, String key, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MAIN_PREFERENCES_KEY , MODE_PRIVATE).edit();
        editor.putString(key, string);
        editor.apply();
    }

    public static String getStringForKey(String key, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(MAIN_PREFERENCES_KEY, MODE_PRIVATE);
        return prefs.getString(key, null);
    }
}
