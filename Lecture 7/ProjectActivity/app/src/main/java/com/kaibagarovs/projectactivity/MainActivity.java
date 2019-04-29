package com.kaibagarovs.projectactivity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(Activity2.KEY_TEXT,"Hello Java Guru!");
        intent.putExtra(Activity2.KEY_INT, 888);
//        startActivity(intent);
        startActivityForResult(intent,333);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 333) {
            String message = "";
            if (resultCode == RESULT_CANCELED) {
                message = "CANCELED";
            } else if (resultCode == RESULT_OK) {
                message = "OK";
            } else if (resultCode == 444) {
                message = "CUSTOM CODE";
            }

            int a = 0;
        }

    }
}
