package com.example.den.lesson6.Interfaces;

import android.app.Activity;

public interface PhotoItemsPresenter {
    /**
     * Show photo items via chosen presenter
     * @param photoItems items to show
     * @param activity activity to show items in
     * @param callback callback on interaction with UI (click, like, etc)
     */
    void setupWithPhotoItems(PhotoItem[] photoItems, Activity activity, PhotoItemsPresenterCallbacks callback);
}
