package com.example.den.lesson11.Interfaces;

public interface NetworkingManager {
    void getPhotoItems(NetworkingResultListener result);
    void fetchNewItemsFromPosition(int lastPosition, NetworkingResultListener result);
}
