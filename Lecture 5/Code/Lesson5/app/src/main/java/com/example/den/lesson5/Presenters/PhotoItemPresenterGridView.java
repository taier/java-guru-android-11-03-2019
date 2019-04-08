package com.example.den.lesson5.Presenters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.den.lesson5.Interfaces.PhotoItem;
import com.example.den.lesson5.Interfaces.PhotoItemsPresenter;
import com.example.den.lesson5.R;
import com.squareup.picasso.Picasso;

public class PhotoItemPresenterGridView implements PhotoItemsPresenter {

    public void setupWithPhotoItems(PhotoItem[] photoItems, Activity activity) {
        ArrayAdapter<PhotoItem> carsAdapter =
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
                        Picasso.get().load(photoItem.getImgUrl()).into(viewHolder.imageViewPhoto);
                        return convertView;

                    }
                };

        GridView cheeseGrid = new GridView(activity);
        activity.setContentView(cheeseGrid);
        cheeseGrid.setNumColumns(2);
        cheeseGrid.setColumnWidth(40);
        cheeseGrid.setVerticalSpacing(20);
        cheeseGrid.setHorizontalSpacing(20);
        cheeseGrid.setAdapter(carsAdapter);
    }
}
