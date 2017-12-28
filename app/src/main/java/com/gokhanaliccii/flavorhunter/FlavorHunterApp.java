package com.gokhanaliccii.flavorhunter;

import android.app.Application;

import com.gokhanaliccii.flavorhunter.rest.RestAdapter;

/**
 * Created by gokhan on 28/12/17.
 */

public class FlavorHunterApp extends Application{


    @Override
    public void onCreate() {
        super.onCreate();


        RestAdapter.getsInstance(this);
    }
}
