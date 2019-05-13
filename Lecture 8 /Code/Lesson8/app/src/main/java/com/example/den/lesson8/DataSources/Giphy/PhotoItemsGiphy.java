package com.example.den.lesson8.DataSources.Giphy;

import com.example.den.lesson8.Interfaces.PhotoItem;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class PhotoItemsGiphy implements PhotoItem  {

    @SerializedName("db_id")
    private transient Long id;

    @SerializedName("id")
    private String imgID;

    ImagesContainer images;
    String username;

    private String imagesForORM;

    public PhotoItemsGiphy() { }

    @Override
    public String getID() {return this.imgID;}

    @Override
    public String getImgUrl() {
        if(images == null) {
            this.images = new Gson().fromJson(this.imagesForORM, ImagesContainer.class);
        }

        return images.getMediumSize();
    }

    @Override
    public String getAuthorName() {
        return username;
    }

    @Override
    public void saveToDatabase() {
        this.imagesForORM = images.toString();
        SugarRecord.save(this);
    }

    @Override
    public void deleteFromDatabase() {
        SugarRecord.deleteAll(PhotoItemsGiphy.class,"img_ID = ?", this.imgID);
    }

    @Override
    public boolean isSavedToDatabase() {
        return SugarRecord.find(PhotoItemsGiphy.class,"img_ID = ?", this.imgID).size() > 0;
    }

    public class ImagesContainer implements Serializable {

        public String toString() {
            return new Gson().toJson(this);
        }

        DownsizedMedium downsized_medium;

        public String getMediumSize() {
            return downsized_medium.url;
        }
    }

    public class DownsizedMedium implements Serializable {
        String url;
    }
}
