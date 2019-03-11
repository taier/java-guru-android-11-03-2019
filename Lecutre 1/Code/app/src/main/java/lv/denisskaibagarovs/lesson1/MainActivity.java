package lv.denisskaibagarovs.lesson1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();

        RelativeLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setBackgroundColor(Color.BLUE);

    }

    public void onButtonPress(View view) {
        Button button = findViewById(R.id.button);
        button.setBackgroundColor(Color.RED);

        int a = 0;
    }
}
