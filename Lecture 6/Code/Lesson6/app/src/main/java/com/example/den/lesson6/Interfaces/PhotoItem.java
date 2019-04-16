package com.example.den.lesson6.Interfaces;

import java.io.Serializable;

public interface PhotoItem extends Serializable {
    /**
     * @return imgUrl (in most cases a good enough quality)
     */
    String getImgUrl();

    /**
     * @return author name of image
     */
    String getAuthorName();
}
