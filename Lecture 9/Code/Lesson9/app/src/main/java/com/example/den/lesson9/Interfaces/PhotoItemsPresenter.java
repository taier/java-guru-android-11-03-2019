package com.example.den.lesson9.Interfaces;

import android.app.Activity;

public interface PhotoItemsPresenter {
    void setupWithPhotoItems(PhotoItem[] photoItems, Activity activity, PhotoItemsPresenterCallbacks callback);
    void updateWithItems(PhotoItem[] photoItems);
}
