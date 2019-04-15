package com.example.den.lesson6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.den.lesson6.Interfaces.PhotoItem;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareActivity extends Activity {

    public static String PHOTO_ITEM_KEY = "PHOTO_ITEM_KEY";

    private static String COMMENT_KEY = "COMMENT_KEY";
    private static int INTENT_SHARE_CODE = 888;

    PhotoItem photoItem;

    @BindView(R.id.imageViewImage) ImageView imageViewImage;
    @BindView(R.id.textViewComment) TextView textViewComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);

        this.photoItem = (PhotoItem)getIntent().getSerializableExtra(PHOTO_ITEM_KEY);

        setupUI();
    }

    private void setupUI() {
        Picasso.get().load(photoItem.getImgUrl()).into(imageViewImage);
    }

    public void onShareButton(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, photoItem.getImgUrl());
        startActivityForResult(Intent.createChooser(i, "Share URL"),INTENT_SHARE_CODE,null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode != INTENT_SHARE_CODE) {
            return;
        }

        if(resultCode == RESULT_CANCELED) {
            Log.v("ShareActivity","Share canceled");
        } else if (resultCode == RESULT_OK) {
            Log.v("ShareActivity","Share succeed");
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textViewComment.setText(savedInstanceState.getString(COMMENT_KEY));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(COMMENT_KEY, textViewComment.getText().toString());
    }

    public void onTestCommentButton(View view) {
        textViewComment.setText("Hello");
    }
}
