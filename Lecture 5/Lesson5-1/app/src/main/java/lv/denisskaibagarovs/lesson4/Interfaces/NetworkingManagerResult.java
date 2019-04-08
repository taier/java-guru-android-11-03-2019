package lv.denisskaibagarovs.lesson4.Interfaces;

import lv.denisskaibagarovs.lesson4.DataSources.Unsplash.PhotoItemUnsplash;

public interface NetworkingManagerResult {
    void callback(PhotoItem[] photoItems);
}
