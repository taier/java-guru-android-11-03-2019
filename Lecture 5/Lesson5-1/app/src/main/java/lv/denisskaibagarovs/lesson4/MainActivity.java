package lv.denisskaibagarovs.lesson4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    //TODO 1 Add dependency in project gradle file for
    // OkHttp
    // Picaso
    // GSON
    // Butterknife

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO 2 using OKHttp create a request with url:
        //"https://api.unsplash.com/photos/?client_id=311ed690d7678d20b8ce556e56d5bf168d6ddf9fa1126e58193d95089d796542"

        final Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        JsonParser parser = new JsonParser();
        final ArrayList<PhotoItem> photoItems = new ArrayList<PhotoItem>();

        Request request = new Request.Builder()
                .url("https://api.unsplash.com/photos/?client_id=311ed690d7678d20b8ce556e56d5bf168d6ddf9fa1126e58193d95089d796542")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    JSONArray array = new JSONArray(jsonData);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject imgObject = array.getJSONObject(i);

                        JsonElement mJson = parser.parse(imgObject.toString());
                        //TODO 3 uisng GSON parse mJson to PhotoItem object
                        PhotoItem photoItem = gson.fromJson(mJson, PhotoItem.class);
                        photoItems.add(photoItem);

                    }
                    runOnUiThread(()-> {
                        setupWithPhotoItems(photoItems.toArray(new PhotoItem[photoItems.size()]));
                    });

                } catch (JSONException ex) {
                    Log.e("JSON-PARSE",ex.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }});
    }

    private void setupWithPhotoItems(final PhotoItem[] photoItems) {
        ArrayAdapter<PhotoItem> carsAdapter =
                new ArrayAdapter<PhotoItem>(this, 0, photoItems) {
                    @Override
                    public View getView(int position,
                                        View convertView,
                                        ViewGroup parent) {
                        PhotoItem photoItem = photoItems[position];
                        // Inflate only once
                        if (convertView == null) {
                            convertView = getLayoutInflater()
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
                        viewHolder.textViewAuthor.setText(photoItem.getAuthorName());

                        //TODO 6 Load image into imageView via Picasso
                        // ImgView - viewHolder.imageViewPhoto
                        // PhotoURL - photoItem.getImgUrl()
                        Picasso.get().load(photoItem.getImgUrl()).into(viewHolder.imageView);
                        return convertView;

                        //TODO 7* Extra!!!
                        // Add text view for "Location" into custom_item_img
                        // Add text view to ViewHolder and bind it to butterknife
                        // Add location to PhotoItem User class so GSON could parse it
                        // Set location to convertView

                    }
                };

        GridView cheeseGrid = new GridView(this);
        setContentView(cheeseGrid);
        cheeseGrid.setNumColumns(2);
        cheeseGrid.setColumnWidth(40);
        cheeseGrid.setVerticalSpacing(20);
        cheeseGrid.setHorizontalSpacing(20);
        cheeseGrid.setAdapter(carsAdapter);


        cheeseGrid.setOnItemClickListener((parent, view, position, id) -> {
            Intent myIntent = new Intent(MainActivity.this, GoogleMap.class);
            myIntent.putExtra("key", photoItems[position].getLocation());
            MainActivity.this.startActivity(myIntent);
        });
    }
}
