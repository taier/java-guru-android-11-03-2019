package lv.denisskaibagarovs.lesson4.Interfaces;
import android.app.Activity;
import lv.denisskaibagarovs.lesson4.DataSources.Unsplash.PhotoItemUnsplash;

public interface PhotoItemsPresenter {
    void showPhotoItems(Activity activity, PhotoItem[] photoItems);
}
