package com.example.den.lesson11.Presenters.GridView;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.den.lesson11.Interfaces.PhotoItem;
import com.example.den.lesson11.Interfaces.PhotoItemsPresenter;
import com.example.den.lesson11.Interfaces.PhotoItemsPresenterCallbacks;
import com.example.den.lesson11.Presenters.ViewHolder;
import com.example.den.lesson11.R;

public class PhotoItemPresenterGridView implements PhotoItemsPresenter {

    private ArrayAdapter<PhotoItem> dataAdapter;
    private PhotoItem[] photoItems;
    private GridView gridView;

    @Override
    public void setupWithPhotoItems(PhotoItem[] items, Activity activity, PhotoItemsPresenterCallbacks callback) {

        if(items.length == 0) {
            activity.setContentView(R.layout.activity_empty);
            Glide.with(activity).load(R.drawable.empty)
                    .into((ImageView)activity.findViewById(R.id.imageViewImage));
            return;
        }

        this.photoItems = items;
        this.dataAdapter =
                new ArrayAdapter<PhotoItem>(activity, 0, photoItems) {
                    @Override
                    public View getView(int position,
                                        View convertView,
                                        ViewGroup parent) {
                        PhotoItem photoItem = photoItems[position];
                        // Inflate only once
                        if (convertView == null) {
                            convertView = activity.getLayoutInflater()
                                    .inflate(R.layout.custom_item_img, null, false);
                        }

                        ViewHolder viewHolder;
                        if(convertView.getTag() == null) {
                            viewHolder = new ViewHolder(convertView);
                            convertView.setTag(viewHolder);
                        } else  {
                            viewHolder = (ViewHolder)convertView.getTag();
                        }

                        viewHolder.textViewAuthor.setText(photoItem.getAuthorName());
                        updateFavoriteButton(viewHolder.imageFavorite, photoItem, activity);

                        viewHolder.imageFavorite.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                callback.onItemToggleFavorite(photoItem);
                                updateFavoriteButton(viewHolder.imageFavorite, photoItem, activity);
                            }
                        });

                        Glide.with(getContext()).load(photoItem.getImgUrl())
                                .thumbnail(Glide.with(getContext()).load(R.drawable.placeholder_loading))
                                .into(viewHolder.imageViewPhoto);

                        return convertView;

                    }

                    @Override
                    public int getCount() {
                        return photoItems.length;
                    }
                };


        gridView = new GridView(activity);
        gridView.setNumColumns(2);
        gridView.setColumnWidth(40);
        gridView.setVerticalSpacing(20);
        gridView.setHorizontalSpacing(20);
        gridView.setAdapter(dataAdapter);
        gridView.setBackgroundColor(activity.getResources().getColor(R.color.colorBackgroundMain));

        activity.setContentView(R.layout.activity_main);
        ((LinearLayout)activity.findViewById(R.id.mainContainer)).addView(gridView);

        gridView.setOnItemClickListener((adapterView, view, i, l) ->{
            callback.onItemSelected(photoItems[i]);
        });

        gridView.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                if(firstVisibleItem + visibleItemCount >= totalItemCount){
                     callback.onLastItemReach(totalItemCount);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState){

            }
        });
    }

    @Override
    public void updateWithItems(PhotoItem[] photoItems) {
        this.photoItems = photoItems;
        dataAdapter.notifyDataSetChanged();
    }


    private void updateFavoriteButton(ImageView imageFavorite, PhotoItem photoItem, Context context) {

        boolean isFavorited = photoItem.isSavedToDatabase();
        if(isFavorited) {
            imageFavorite.setImageResource(R.drawable.favorite_on);
        } else {
            imageFavorite.setImageResource(R.drawable.favorite_off);
        }
    }
}
