package com.example.den.lesson5.DataSources.Unsplash;

import com.example.den.lesson5.Interfaces.PhotoItem;

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

    public class User {
        String name;
    }
}
