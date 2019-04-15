package com.example.den.lesson6.DataSources.Unsplash;

import android.util.Log;

import com.example.den.lesson6.Interfaces.NetworkingManager;
import com.example.den.lesson6.Interfaces.NetworkingResultListener;
import com.example.den.lesson6.Interfaces.PhotoItem;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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

public class NetworkingManagerUnsplash implements NetworkingManager {

    @Override
    public void getPhotoItems(NetworkingResultListener result) {

        Request request = new Request.Builder()
                .url("https://api.unsplash.com/photos/?client_id=311ed690d7678d20b8ce556e56d5bf168d6ddf9fa1126e58193d95089d796542")
                .build();

        final Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        JsonParser parser = new JsonParser();
        final ArrayList<PhotoItem> photoItems = new ArrayList<PhotoItem>();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    JSONArray array = new JSONArray(jsonData);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject imgObject = array.getJSONObject(i);

                        JsonElement mJson = parser.parse(imgObject.toString());
                        PhotoItem photoItem = gson.fromJson(mJson, PhotoItemUnsplash.class);
                        photoItems.add(photoItem);
                    }
                } catch (Exception ex) {
                    Log.e("ERROR",ex.getLocalizedMessage());
                } finally {
                    result.callback(photoItems.toArray(new PhotoItem[photoItems.size()]));
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }});
    }
}
