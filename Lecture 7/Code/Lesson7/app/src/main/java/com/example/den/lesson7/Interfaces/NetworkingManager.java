package com.example.den.lesson7.Interfaces;

public interface NetworkingManager {
    void getPhotoItems(NetworkingResultListener result);
    void fetchNewItemsFromPosition(int lastPosition, NetworkingResultListener result);
}
