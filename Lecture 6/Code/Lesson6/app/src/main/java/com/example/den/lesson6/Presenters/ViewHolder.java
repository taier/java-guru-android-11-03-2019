package com.example.den.lesson6.Presenters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.den.lesson6.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by den on 3/25/18.
 */

public class ViewHolder {
    @BindView(R.id.imageView) ImageView imageViewPhoto;
    @BindView(R.id.textViewAuthor) TextView textViewAuthor;

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
