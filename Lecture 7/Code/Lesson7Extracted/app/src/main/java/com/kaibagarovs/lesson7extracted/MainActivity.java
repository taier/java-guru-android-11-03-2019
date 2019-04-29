package com.kaibagarovs.lesson7extracted;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {

    private static String KEY_NAME = "KEY_NAME_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testWriteToExternalFile();
    }


    private void testWriteToExternalFile() {
        File path = getExternalStorageDirectory();
//        dont forget to add <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

        if(isStoragePermissionGranted()) { // check if storage is permission is granted
            File file = new File(path, "my_awesome_file.txt"); // file to write
            try {
                FileOutputStream stream = new FileOutputStream(file); // open stream to write
                stream.write("EXTERNAL: Today is a great day!".getBytes()); // try to write
                stream.close(); // close stream
            } catch (Exception ex) {
                Log.v("i",ex.getLocalizedMessage()); // if something went wrong - print error
            }
        }
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Need to ask only after API 23
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Storage","Permission is granted");
                return true;
            } else {
                Log.v("Storage","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Storage","Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){ // check permission result
            Log.v("STORAGE","Permission: "+permissions[0]+ "was "+grantResults[0]);
            testWriteToExternalFile(); // execute work
        }
    }


    private void testWriteToLocalFile() {

        // Local phone
        File path = getFilesDir();

        // Local SD cards
//        File path = getExternalFilesDir("super_private");

        File file = new File(path, "my_awesome_file.txt"); // file to write
        try {
            FileOutputStream stream = new FileOutputStream(file); // open stream to read from
            stream.write("LOCAL: Today is a great day!".getBytes()); // write to file
            stream.close(); // close stream
        } catch (Exception ex) {
            Log.v("i",ex.getLocalizedMessage()); // if something went wrong - print error
        }
    }

    private void testReadFromLocalFile() {

        // Get folder on device
        File path = getFilesDir();
        File file = new File(path, "my_awesome_file.txt"); // file to read from
        int length = (int) file.length(); // size of file to read
        byte[] bytes = new byte[length];  // number of bytes to read

        try {
            FileInputStream in = new FileInputStream(file); // open stream to read from
            in.read(bytes); // try to read
            in.close(); // close stream
        } catch (Exception ex) {
            Log.v("i",ex.getLocalizedMessage()); // if something went wrong - print error
        }

        String contents = new String(bytes); // converts bytes to string
        Log.i("i", contents); // print content
    }



    private void testSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("MY_PREFERENCES",
                MODE_PRIVATE);
        String name = preferences.getString(KEY_NAME,"DEFAULT");
        if(name.equals("DEFAULT")) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_NAME,"Deniss");
            editor.apply();
        }
    }
}


