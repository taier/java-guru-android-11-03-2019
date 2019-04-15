package com.example.den.lesson6.DataSources.Unsplash;

import com.example.den.lesson6.Interfaces.PhotoItem;

import java.io.Serializable;
import java.util.Map;

public class PhotoItemUnsplash implements PhotoItem {

    Map<String,String> urls;
    User user;

    public String getImgUrl() {
        return this.urls.get("regular");
    }

    public String getAuthorName() {
        return this.user.name;
    }

    public class User implements Serializable {
        String name;
    }
}
