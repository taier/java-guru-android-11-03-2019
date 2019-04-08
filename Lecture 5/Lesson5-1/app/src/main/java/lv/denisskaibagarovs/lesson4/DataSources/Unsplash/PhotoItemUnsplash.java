package lv.denisskaibagarovs.lesson4.DataSources.Unsplash;

import java.util.Map;

import lv.denisskaibagarovs.lesson4.Interfaces.PhotoItem;

public class PhotoItemUnsplash implements PhotoItem {
    Map<String,String> urls;
    User user;

    @Override
    public String getImgUrl() {
        return this.urls.get("regular");
    }

    @Override
    public String getUserName(){
        return this.user.name;
    }

    public class User {
        String name;
    }
}
