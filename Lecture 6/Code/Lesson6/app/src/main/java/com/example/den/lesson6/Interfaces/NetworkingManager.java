package com.example.den.lesson6.Interfaces;

public interface NetworkingManager {
    /**
     * Initiate an images download
     * @param result result listened for download
     */
    void getPhotoItems(NetworkingResultListener result);
}
