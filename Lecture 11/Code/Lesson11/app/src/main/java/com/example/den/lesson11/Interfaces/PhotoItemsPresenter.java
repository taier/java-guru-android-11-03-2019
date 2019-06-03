package com.example.den.lesson11.Interfaces;

import android.app.Activity;

public interface PhotoItemsPresenter {
    void setupWithPhotoItems(PhotoItem[] photoItems, Activity activity, PhotoItemsPresenterCallbacks callback);
    void updateWithItems(PhotoItem[] photoItems);
}
