package com.gokhanaliccii.flavorhunter;

import android.app.Application;

import com.gokhanaliccii.flavorhunter.components.log.Logger;
import com.gokhanaliccii.flavorhunter.rest.api.RestApi;
import com.gokhanaliccii.flavorhunter.variant.App;
import com.gokhanaliccii.flavorhunter.variant.ApplicationVariant;

/**
 * Created by gokhan on 28/12/17.
 */

public class FlavorHunterApp extends Application {

    private static FlavorHunterApp sApp;

    private App appVariant;

    @Override
    public void onCreate() {
        super.onCreate();

        sApp = this;
        appVariant = new ApplicationVariant().init(this);

    }

    @Override
    public void onTerminate() {
        sApp = null;
        super.onTerminate();
    }


    public static Logger logger() {
        return sApp.appVariant.logger();
    }

    public static RestApi restApi() {
        return sApp.appVariant.restApi();
    }

}
