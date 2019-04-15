package com.example.den.lesson6.Interfaces;

import android.app.Activity;

public interface PhotoItemsPresenter {
    void setupWithPhotoItems(PhotoItem[] photoItems, Activity activity, PhotoItemsPresenterCallbacks callback);
}
