package com.example.den.lesson3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderCar {
    @BindView(R.id.imageViewCar) ImageView imageViewCar;
    @BindView(R.id.textViewText) TextView textViewText;

    public ViewHolderCar(View view) {
        ButterKnife.bind(this, view);
    }
}
