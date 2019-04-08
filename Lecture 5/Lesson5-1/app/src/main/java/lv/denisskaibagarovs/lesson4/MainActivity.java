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

import lv.denisskaibagarovs.lesson4.DataSources.Unsplash.NetworkingManagerUnsplash;
import lv.denisskaibagarovs.lesson4.Interfaces.NetworkingManager;
import lv.denisskaibagarovs.lesson4.Interfaces.NetworkingManagerResult;
import lv.denisskaibagarovs.lesson4.Interfaces.PhotoItemsPresenter;
import lv.denisskaibagarovs.lesson4.Presentors.GridView.PhotoItemsPresenterGridView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NetworkingManager manager = new NetworkingManagerUnsplash(this);
        PhotoItemsPresenter presenter = new PhotoItemsPresenterGridView();

        manager.getImages(photoItems -> {
            runOnUiThread(()-> {
                presenter.showPhotoItems(this, photoItems);
            });
        });
    }
}
