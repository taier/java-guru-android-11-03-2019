package com.example.den.lesson10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.den.lesson10.DataSources.Giphy.NetworkingManagerGiphy;
import com.example.den.lesson10.DataSources.Local.NetworkingManagerLocal;
import com.example.den.lesson10.DataSources.Unsplash.NetworkingManagerUnsplash;
import com.example.den.lesson10.Interfaces.NetworkingManager;
import com.example.den.lesson10.Interfaces.PhotoItem;
import com.example.den.lesson10.Interfaces.PhotoItemsPresenter;
import com.example.den.lesson10.Interfaces.PhotoItemsPresenterCallbacks;
import com.example.den.lesson10.Presenters.GridView.PhotoItemPresenterGridView;
import com.example.den.lesson10.Presenters.RecyclerView.PhotoPresenterRecyclerView;


public class MainActivity extends Activity implements PhotoItemsPresenterCallbacks {

    public enum ImgServices {
        UNSPLASH,
        GIPHY,
        FAVORITE
    }

    private NetworkingManager networkingManager;
    private PhotoItemsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showImgService(ImgServices.GIPHY);
    }

    private void showImgService(ImgServices service) {

        switch (service) {
            case GIPHY:
                networkingManager = new NetworkingManagerGiphy();
                break;
            case UNSPLASH:
                networkingManager = new NetworkingManagerUnsplash();
                break;
            case FAVORITE:
                networkingManager = new NetworkingManagerLocal();
        }

        this.presenter = new PhotoItemPresenterGridView();
//        this.presenter = new PhotoPresenterRecyclerView();
        this.networkingManager.getPhotoItems(photoItems ->
                runOnUiThread(()-> {
                    presenter.setupWithPhotoItems(photoItems,this, this);
                })
        );
    }

    @Override
    public void onItemSelected(PhotoItem item) {
        Intent shareIntent = new Intent(this, ShareActivityWithFragments.class);
        shareIntent.putExtra(ShareActivityWithFragments.PHOTO_ITEM_KEY,item);
        startActivity(shareIntent);
    }

    @Override
    public void onItemToggleFavorite(PhotoItem item) {
        testFavoriteORM(item);
    }

    @Override
    public void onLastItemReach(int position) {
        networkingManager.fetchNewItemsFromPosition(position, photoItems -> {
            runOnUiThread(()-> {
                presenter.updateWithItems(photoItems);
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);

        final MenuItem favoriteMenuItem = menu.findItem(R.id.action_show_favotites);
        favoriteMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                showImgService(ImgServices.FAVORITE);
                return true;
            }
        });

        final MenuItem showUnsplashMenuItem = menu.findItem(R.id.action_show_unslash);
        showUnsplashMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                showImgService(ImgServices.UNSPLASH);
                return true;
            }
        });

        final MenuItem showUnsplashGiphyItem = menu.findItem(R.id.action_show_giphy);
        showUnsplashGiphyItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                showImgService(ImgServices.GIPHY);
                return true;
            }
        });

        return true;
    }

    // *****************************************************
    // *****************************************************
    // ************************ ORM ************************
    // *****************************************************
    // *****************************************************

    private void testFavoriteORM(PhotoItem item) {

        if(item.isSavedToDatabase()) {
            item.deleteFromDatabase();

            // Remove favorite from screen if unfavorite from favorite screen
            if (networkingManager.getClass() == NetworkingManagerLocal.class) {
                showImgService(ImgServices.FAVORITE);
            }
        } else {
            item.saveToDatabase();
        }
    }
}
