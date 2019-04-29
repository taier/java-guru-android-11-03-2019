package com.kaibagarovs.projectfragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity implements Fragment2.OnFragmentInteractionListener {


    class PhotoItem {
        String author;
        String url;

        PhotoItem(String a, String u) {
            this.author = a;
            this.url = u;
        }
    }

    PhotoItem photoItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.photoItem = new PhotoItem("Thom Smith", "google.com");


        showFragment(new Fragment1(),true);
        showFragment(new Fragment2(),false);

//        showFragment2(true);
//        showFragment1(true);
    }

    void showFragment1(boolean inTopView) {
        Fragment1 fragment = new Fragment1();
        fragment.photoItem = photoItem;

        getSupportFragmentManager()
                .beginTransaction()
                .add(inTopView ? R.id.frameLayout1 : R.id.frameLayout2, fragment)
                .commit();
    }

    void showFragment(BaseFragment fragment, boolean inTopView) {
        fragment.photoItem = this.photoItem;
        getSupportFragmentManager()
                .beginTransaction()
                .add(inTopView ? R.id.frameLayout1 : R.id.frameLayout2, fragment)
                .commit();
    }

    void showFragment2(boolean add) {

        if(add) {
            Fragment2 fragment = new Fragment2();
            fragment.photoItem = photoItem;

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayout2, fragment)
                    .commit();

        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(getSupportFragmentManager().getFragments().get(
                            getSupportFragmentManager().getFragments().size() - 1
                    ))
                    .commit();
        }
    }

    public void onFragmentFinishJob(int jobID) {
        if (jobID == 22) {

        } else if (jobID == 33) {

        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK
                && getSupportFragmentManager().getFragments().size() > 1) {
            showFragment2(false);
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        showFragment1(false);
    }
}
