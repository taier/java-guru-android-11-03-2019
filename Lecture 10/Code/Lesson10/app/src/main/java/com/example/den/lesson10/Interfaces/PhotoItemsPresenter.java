package com.example.den.lesson10.Interfaces;

import android.app.Activity;
import android.widget.LinearLayout;

public interface PhotoItemsPresenter {
    void setupWithPhotoItems(PhotoItem[] photoItems,
                             Activity activity,
                             LinearLayout layoutToPresent,
                             PhotoItemsPresenterCallbacks callback);
    void updateWithItems(PhotoItem[] photoItems);
}
