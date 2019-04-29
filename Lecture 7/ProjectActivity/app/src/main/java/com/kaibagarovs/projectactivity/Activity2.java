package com.kaibagarovs.projectactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    public static String KEY_TEXT = "Activity2TEXT";
    public static String KEY_INT = "Activity2INT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        String someText = getIntent().getStringExtra(KEY_TEXT);
        int someInt = getIntent().getIntExtra(KEY_INT,0);
        TextView textViewActivity2 = findViewById(R.id.textViewActivity2);
        textViewActivity2.setText(someText + someInt);
    }

    public void onTextClick(View view) {
        setResult(444);
        finish();
    }
}
