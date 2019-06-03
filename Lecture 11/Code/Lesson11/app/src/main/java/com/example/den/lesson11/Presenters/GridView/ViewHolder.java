package com.example.den.lesson11.Presenters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.den.lesson11.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by den on 3/25/18.
 */

public class ViewHolder {
    @BindView(R.id.imageView)
    public ImageView imageViewPhoto;
    @BindView(R.id.textViewAuthor)
    public TextView textViewAuthor;
    @BindView(R.id.imageFavorite)
    public ImageView imageFavorite;

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
