package com.example.den.lesson9.Interfaces;

public interface NetworkingManager {
    void getPhotoItems(NetworkingResultListener result);
    void fetchNewItemsFromPosition(int lastPosition, NetworkingResultListener result);
}
