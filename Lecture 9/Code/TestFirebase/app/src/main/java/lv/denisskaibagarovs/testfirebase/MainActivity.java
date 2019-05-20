package lv.denisskaibagarovs.testfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        "crash".substring(10);

    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics.getInstance(this).setCurrentScreen(this, "Main", this.getClass().getSimpleName());
    }

    public void onButton1Press(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "button_1");
        FirebaseAnalytics.getInstance(this).logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}
