package com.gokhanaliccii.flavorhunter.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gokhan on 27/12/17.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";


    protected FragmentManager fragmentManager(){
        return getSupportFragmentManager();
    }

    protected FragmentTransaction fragmentTransaction(){
        return fragmentManager().beginTransaction();
    }

}