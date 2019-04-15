package com.example.den.lesson6.DataSources.Giphy;

import com.example.den.lesson6.Interfaces.PhotoItem;

import java.io.Serializable;
import java.util.Map;

public class PhotoItemsGiphy implements PhotoItem {

    ImagesContainer images;
    String username;

    @Override
    public String getImgUrl() {
        return images.getMediumSize();
    }

    @Override
    public String getAuthorName() {
        return username;
    }

    class ImagesContainer implements Serializable {
        Map<String,String> downsized_medium;

        public String getMediumSize() {
            return downsized_medium.get("url");
        }
    }
}
