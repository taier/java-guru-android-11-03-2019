package com.example.den.lesson10.Interfaces;

public interface NetworkingManager {
    void getPhotoItems(NetworkingResultListener result);
    void fetchNewItemsFromPosition(int lastPosition, NetworkingResultListener result);
}
