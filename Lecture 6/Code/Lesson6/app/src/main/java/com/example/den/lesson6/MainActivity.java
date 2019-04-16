package com.example.den.lesson6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.den.lesson6.DataSources.Giphy.NetworkingManagerGiphy;
import com.example.den.lesson6.DataSources.Unsplash.NetworkingManagerUnsplash;
import com.example.den.lesson6.Interfaces.NetworkingManager;
import com.example.den.lesson6.Interfaces.PhotoItem;
import com.example.den.lesson6.Interfaces.PhotoItemsPresenter;
import com.example.den.lesson6.Interfaces.PhotoItemsPresenterCallbacks;
import com.example.den.lesson6.Presenters.PhotoItemPresenterGridView;

public class MainActivity extends Activity implements PhotoItemsPresenterCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set needed networking manager
        NetworkingManager networkingManager = new NetworkingManagerGiphy();
//        NetworkingManager networkingManager = new NetworkingManagerUnsplash();

        // Set needed presenter
        PhotoItemsPresenter presenter = new PhotoItemPresenterGridView();

        // Ask networking manager for images
        networkingManager.getPhotoItems(photoItems ->
                runOnUiThread(()-> { // when items are ready
                    // present them via presenter
                    presenter.setupWithPhotoItems(photoItems,this, this);
            })
        );
    }

    @Override
    public void onItemSelected(PhotoItem item) {
        Intent shareIntent = BaseActivity.buildIntent(this, item, ShareActivityWithFragments.class);
        startActivity(shareIntent);
    }
}
