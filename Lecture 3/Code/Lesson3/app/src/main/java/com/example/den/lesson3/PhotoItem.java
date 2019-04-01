package com.example.den.lesson3;

import java.security.PublicKey;
import java.util.Map;

public class PhotoItem {

    Map<String,String> urls;
    User user;

    public String getImgUrl() {
        return urls.get("regular");
    }

    public String getName() {
        return  user.name;
    }

    class User {
        String name;
    }
}
