package com.example.den.lesson8.Interfaces;

public interface NetworkingManager {
    void getPhotoItems(NetworkingResultListener result);
    void fetchNewItemsFromPosition(int lastPosition, NetworkingResultListener result);
}
