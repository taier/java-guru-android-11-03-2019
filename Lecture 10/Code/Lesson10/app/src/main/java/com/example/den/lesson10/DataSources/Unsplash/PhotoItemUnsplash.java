package com.example.den.lesson10.DataSources.Unsplash;

import com.example.den.lesson10.Interfaces.PhotoItem;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class PhotoItemUnsplash implements PhotoItem {

    @SerializedName("db_id")
    private transient Long id = null;

    @SerializedName("id")
    private String imgID;

    URLs urls;
    User user;

    private String URLsFromORM;
    private String UserFromORM;

    public PhotoItemUnsplash() {}


    public String getID () {return this.imgID;}

    public String getImgUrl() {
        if (urls == null) {
            this.urls = new Gson().fromJson(this.URLsFromORM, URLs.class);
        }

        return this.urls.regular;
    }

    public String getAuthorName() {
        if (user == null) {
            this.user = new Gson().fromJson(this.UserFromORM, User.class);
        }

        return this.user.name;
    }

    @Override
    public void saveToDatabase() {
        this.URLsFromORM = urls.toString();
        this.UserFromORM = user.toString();

        SugarRecord.save(this);


    }

    @Override
    public void deleteFromDatabase() {
        SugarRecord.deleteAll(PhotoItemUnsplash.class,"img_ID = ?", this.imgID);
    }

    @Override
    public boolean isSavedToDatabase() {
        return SugarRecord.find(PhotoItemUnsplash.class,"img_ID = ?", this.imgID).size() > 0;
    }

    public class User implements Serializable {
        String name;

        public String toString() {
            return new Gson().toJson(this);
        }
    }

    public class URLs implements Serializable {
        String regular;

        public String toString() {
            return new Gson().toJson(this);
        }

    }
}
