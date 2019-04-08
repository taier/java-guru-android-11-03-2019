package lv.denisskaibagarovs.lesson4.DataSources.Unsplash;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import lv.denisskaibagarovs.lesson4.Interfaces.NetworkingManager;
import lv.denisskaibagarovs.lesson4.Interfaces.NetworkingManagerResult;
import lv.denisskaibagarovs.lesson4.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkingManagerUnsplash implements NetworkingManager {

    private String api_url = "";
    private Activity activity;

    public NetworkingManagerUnsplash(Activity activity) {
        api_url = activity.getString(R.string.unsplash_url);
    }

    @Override
    public void getImages(NetworkingManagerResult resultCallback) {
        final Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        JsonParser parser = new JsonParser();
        final ArrayList<PhotoItemUnsplash> photoItemUnsplashes = new ArrayList<PhotoItemUnsplash>();

        Request request = new Request.Builder()
                .url(api_url)
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
                        //TODO 3 uisng GSON parse mJson to PhotoItemUnsplash object
                        PhotoItemUnsplash photoItemUnsplash = gson.fromJson(mJson, PhotoItemUnsplash.class);
                        photoItemUnsplashes.add(photoItemUnsplash);

                    }

                    resultCallback.callback(photoItemUnsplashes.toArray(new PhotoItemUnsplash[photoItemUnsplashes.size()]));


                } catch (JSONException ex) {
                    Log.e("JSON-PARSE",ex.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }});
    }
}
