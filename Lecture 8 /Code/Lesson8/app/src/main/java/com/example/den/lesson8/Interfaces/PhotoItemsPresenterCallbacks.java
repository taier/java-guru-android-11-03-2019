package com.example.den.lesson8.Interfaces;

public interface PhotoItemsPresenterCallbacks {
    void onItemSelected(PhotoItem item);
    void onItemToggleFavorite(PhotoItem item);
    void onLastItemReach(int position);
}
