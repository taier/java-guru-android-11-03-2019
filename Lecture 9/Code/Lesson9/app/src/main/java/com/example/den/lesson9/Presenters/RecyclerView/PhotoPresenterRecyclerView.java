package com.example.den.lesson9.Presenters.RecyclerView;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


import com.example.den.lesson9.Interfaces.PhotoItem;
import com.example.den.lesson9.Interfaces.PhotoItemsPresenter;
import com.example.den.lesson9.Interfaces.PhotoItemsPresenterCallbacks;

public class PhotoPresenterRecyclerView implements PhotoItemsPresenter {

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;

    @Override
    public void setupWithPhotoItems(PhotoItem[] photoItems, Activity activity, PhotoItemsPresenterCallbacks callback) {

        this.mRecyclerView = new RecyclerView(activity);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        this.mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(activity,2);
        this.mRecyclerView.setLayoutManager(mLayoutManager);

        this.mAdapter = new Adapter(photoItems);
        this.mRecyclerView.setAdapter(mAdapter);

        this.mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisibleItems = ((GridLayoutManager)mLayoutManager).findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    callback.onLastItemReach(totalItemCount);
                }
            }
        });

        activity.setContentView(mRecyclerView);
    }

    @Override
    public void updateWithItems(PhotoItem[] photoItems) {
        this.mAdapter.photoItems = photoItems;
        this.mAdapter.notifyDataSetChanged();
    }
}
