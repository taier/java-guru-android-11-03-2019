package com.example.den.lesson8.Presenters;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.den.lesson8.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by den on 3/25/18.
 */

public class ViewHolder {
    @BindView(R.id.imageView) ImageView imageViewPhoto;
    @BindView(R.id.textViewAuthor) TextView textViewAuthor;
    @BindView(R.id.imageFavorite) ImageView imageFavorite;

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
