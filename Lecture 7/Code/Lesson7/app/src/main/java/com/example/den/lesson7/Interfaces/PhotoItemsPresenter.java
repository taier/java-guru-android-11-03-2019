package com.example.den.lesson7.Interfaces;

import android.app.Activity;

public interface PhotoItemsPresenter {
    void setupWithPhotoItems(PhotoItem[] photoItems, Activity activity, PhotoItemsPresenterCallbacks callback);
    void updateWithItems(PhotoItem[] photoItems);
}
