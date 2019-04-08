package lv.denisskaibagarovs.lesson4.Presentors.GridView;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.squareup.picasso.Picasso;

import lv.denisskaibagarovs.lesson4.Interfaces.PhotoItem;
import lv.denisskaibagarovs.lesson4.Interfaces.PhotoItemsPresenter;
import lv.denisskaibagarovs.lesson4.R;
import lv.denisskaibagarovs.lesson4.ViewHolder;

public class PhotoItemsPresenterGridView implements PhotoItemsPresenter {

    @Override
    public void showPhotoItems(Activity activity, PhotoItem[] photoItems) {

        ArrayAdapter<PhotoItem> carsAdapter =
                new ArrayAdapter<PhotoItem>(activity, 0, photoItems) {
                    @Override
                    public View getView(int position,
                                        View convertView,
                                        ViewGroup parent) {
                        PhotoItem photoItem = photoItems[position];
                        // Inflate only once
                        if (convertView == null) {
                            convertView = activity.getLayoutInflater()
                                    .inflate(R.layout.custom_item_img, null, false);
                        }

                        ViewHolder viewHolder;
                        if(convertView.getTag() == null) {
                            // TODO 4 Map ViewHolder elements via Butterknife
                            viewHolder = new ViewHolder(convertView);
                            convertView.setTag(viewHolder);
                        } else  {
                            viewHolder = (ViewHolder)convertView.getTag();
                        }

                        //TODO 5 Write function to get an author name from photo item (inside photo Item)
                        viewHolder.textViewAuthor.setText(photoItem.getUserName());

                        //TODO 6 Load image into imageView via Picasso
                        // ImgView - viewHolder.imageViewPhoto
                        Picasso.get().load(photoItem.getImgUrl()).into(viewHolder.imageView);
                        return convertView;
                    }
                };

        GridView cheeseGrid = new GridView(activity);
        activity.setContentView(cheeseGrid);
        cheeseGrid.setNumColumns(2);
        cheeseGrid.setColumnWidth(40);
        cheeseGrid.setVerticalSpacing(20);
        cheeseGrid.setHorizontalSpacing(20);
        cheeseGrid.setAdapter(carsAdapter);
    }
}
