package com.example.den.lesson9;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.den.lesson9.Fragments.InfoFragment;
import com.example.den.lesson9.Fragments.ShareFragment;
import com.example.den.lesson9.Interfaces.PhotoItem;

public class ShareActivityWithFragments extends Activity implements ShareFragment.ShareFragmentListener {

    private PhotoItem photoItem;

    public static String PHOTO_ITEM_KEY = "PHOTO_ITEM_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_with_fragments);

        this.photoItem = (PhotoItem)getIntent().getSerializableExtra(PHOTO_ITEM_KEY);

        showShareFragment();
    }

    private void showShareFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ShareFragment fragment = new ShareFragment();
        fragment.photoItem = this.photoItem;

        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

    private void showInfoFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        InfoFragment fragment = new InfoFragment();

        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

    // Share Fragment Listener
    @Override
    public void onInfoPress() {
        showInfoFragment();
    }

    @Override
    public void onSharePress() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, photoItem.getImgUrl());
        startActivity(Intent.createChooser(i, "Share URL"));
    }

    // Info Fragment Listener

}
