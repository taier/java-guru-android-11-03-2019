package com.example.den.lesson6.Presenters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.den.lesson6.Interfaces.PhotoItem;
import com.example.den.lesson6.Interfaces.PhotoItemsPresenter;
import com.example.den.lesson6.Interfaces.PhotoItemsPresenterCallbacks;
import com.example.den.lesson6.R;
import com.squareup.picasso.Picasso;

public class PhotoItemPresenterGridView implements PhotoItemsPresenter {

    @Override
    public void setupWithPhotoItems(PhotoItem[] photoItems, Activity activity, PhotoItemsPresenterCallbacks callback) {
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

        GridView gridView = new GridView(activity);
        activity.setContentView(gridView);
        gridView.setNumColumns(2);
        gridView.setColumnWidth(40);
        gridView.setVerticalSpacing(20);
        gridView.setHorizontalSpacing(20);
        gridView.setAdapter(carsAdapter);


        gridView.setOnItemClickListener((adapterView, view, i, l) ->{
            callback.onItemSelected(photoItems[i]);
        });
    }
}
