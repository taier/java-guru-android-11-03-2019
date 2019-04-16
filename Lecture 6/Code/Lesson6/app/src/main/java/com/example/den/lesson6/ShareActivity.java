package com.example.den.lesson6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareActivity extends BaseActivity {

    @BindView(R.id.imageViewImage) ImageView imageViewImage;
    @BindView(R.id.textViewComment) TextView textViewComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);

        setupUI();
    }

    private void setupUI() {
        Picasso.get().load(photoItem.getImgUrl()).into(imageViewImage);
    }

    public void onShareButton(View view) {
        // Create intent with action
        Intent i = new Intent(Intent.ACTION_SEND);
        // Set additions
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, photoItem.getImgUrl());
        // Start intent
        startActivityForResult(Intent.createChooser(i, "Share URL"),INTENT_SHARE_CODE,null);
    }

    // Handle on activity result
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

    // Save and restore state of items
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
