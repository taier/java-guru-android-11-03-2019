package com.example.den.lesson5;

import android.app.Activity;
import android.os.Bundle;

import com.example.den.lesson5.DataSources.Unsplash.NetworkingManagerUnsplash;
import com.example.den.lesson5.Interfaces.NetworkingManager;
import com.example.den.lesson5.Interfaces.PhotoItemsPresenter;
import com.example.den.lesson5.Presenters.PhotoItemPresenterGridView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        NetworkingManager networkingManager = new NetworkingManagerGiphy();
        NetworkingManager networkingManager = new NetworkingManagerUnsplash();

        PhotoItemsPresenter presenter = new PhotoItemPresenterGridView();

        networkingManager.getPhotoItems(photoItems ->
                runOnUiThread(()-> {
                    presenter.setupWithPhotoItems(photoItems,this);
            })
        );
    }

    // TODO 1 - Create a new NetworkingManagerGiphy that implements NetworkingManager with url https://api.giphy.com/v1/stickers/trending?api_key=VvyONhZ6eUFDFtuwg7w9tUYXzgefYdYy&limit=25&rating=G
    // TODO 2 - Create a new PhotoItemsGiphy that implements PhotoItems
    // TODO 3 - Parse Giphy response to PhotoItemsGiphy. Note, the url for img is inside images->downsized_medium->url
    // TODO 4* - Create a new PhotoItemsPresenterListView that implement PhotoItemsPresenter and shows images in ListView
    // TODO 5 - Show Giphy images in App :)
}
