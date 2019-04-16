package com.example.den.lesson6.Interfaces;

public interface NetworkingResultListener {
    /**
     * Result of download
     * @param photoItems downloaded items
     */
    void callback(PhotoItem[] photoItems);
}
