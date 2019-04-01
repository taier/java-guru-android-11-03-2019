package com.example.den.lesson3;

import android.graphics.drawable.Drawable;

public class CarObject {
    String name;
    Drawable imageDrawable;

    public CarObject(String carName, Drawable carImage) {
        this.name = carName;
        this.imageDrawable = carImage;
    }
}
