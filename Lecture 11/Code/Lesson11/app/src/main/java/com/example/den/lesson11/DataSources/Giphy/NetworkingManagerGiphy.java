package com.example.den.lesson11.DataSources.Giphy;

import android.util.Log;

import com.example.den.lesson11.Interfaces.NetworkingManager;
import com.example.den.lesson11.Interfaces.NetworkingResultListener;
import com.example.den.lesson11.Interfaces.PhotoItem;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkingManagerGiphy implements NetworkingManager {

    private ArrayList<PhotoItem> photoItems = new ArrayList<>();
    private int limit = 25;
    private int offset = 0;
    private boolean requestInProgress = false;

    @Override
    public void getPhotoItems(NetworkingResultListener result) {
        getItems(result);
    }

    private void getItems(NetworkingResultListener result) {
        Request request = new Request.Builder()
                .url("https://api.giphy.com/v1/stickers/trending?api_key=VvyONhZ6eUFDFtuwg7w9tUYXzgefYdYy&limit="+ limit + "&offset="+ offset +"&rating=G")
                .build();

        final Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        JsonParser parser = new JsonParser();

        requestInProgress = true;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    String json = response.body().string();

                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray array = jsonObject.getJSONArray("data");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject imgObject = array.getJSONObject(i);

                        JsonElement mJson = parser.parse(imgObject.toString());
                        PhotoItem photoItem = gson.fromJson(mJson, PhotoItemsGiphy.class);
                        photoItems.add(photoItem);
                    }
                } catch (Exception ex) {
                    Log.e("ERROR", ex.getLocalizedMessage());
                } finally {
                    requestInProgress = false;
                    result.callback(photoItems.toArray(new PhotoItem[photoItems.size()]));
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }

    @Override
    public void fetchNewItemsFromPosition(int lastPosition, NetworkingResultListener result) {
        if(requestInProgress) {
            return;
        }

        if(offset <= lastPosition) {
            requestInProgress = true;
            offset += limit;
            getItems(result);
        }
    }
}
