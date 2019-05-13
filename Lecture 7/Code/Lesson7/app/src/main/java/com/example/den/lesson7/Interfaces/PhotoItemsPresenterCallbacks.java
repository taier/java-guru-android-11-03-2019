package com.example.den.lesson7.Interfaces;

public interface PhotoItemsPresenterCallbacks {
    void onItemSelected(PhotoItem item);
    void onItemToggleFavorite(PhotoItem item);
    void onLastItemReach(int position);
}
