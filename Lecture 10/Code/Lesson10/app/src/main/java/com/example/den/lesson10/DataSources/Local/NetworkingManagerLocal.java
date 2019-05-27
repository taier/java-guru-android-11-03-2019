package com.example.den.lesson10.DataSources.Local;

import com.example.den.lesson10.DataSources.Giphy.PhotoItemsGiphy;
import com.example.den.lesson10.DataSources.Unsplash.PhotoItemUnsplash;
import com.example.den.lesson10.Interfaces.NetworkingManager;
import com.example.den.lesson10.Interfaces.NetworkingResultListener;
import com.example.den.lesson10.Interfaces.PhotoItem;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class NetworkingManagerLocal implements NetworkingManager {
    @Override
    public void getPhotoItems(NetworkingResultListener result) {

        List<PhotoItem> allFavoritedItems = new ArrayList<PhotoItem>();
        allFavoritedItems.addAll(SugarRecord.listAll(PhotoItemsGiphy.class));
        allFavoritedItems.addAll(SugarRecord.listAll(PhotoItemUnsplash.class));

        result.callback( allFavoritedItems.toArray(new PhotoItem[allFavoritedItems.size()]));
    }

    @Override
    public void fetchNewItemsFromPosition(int lastPosition, NetworkingResultListener result) {

    }
}
