package com.gokhanaliccii.flavorhunter.variant;

import android.content.Context;

import com.gokhanaliccii.flavorhunter.components.log.EmptyLogger;
import com.gokhanaliccii.flavorhunter.components.log.Logger;
import com.gokhanaliccii.flavorhunter.rest.RestAdapter;
import com.gokhanaliccii.flavorhunter.rest.api.RestApi;

/**
 * Created by gokhan on 28/12/17.
 */

public class ApplicationVariant implements App {

    private Logger mLogger;
    private RestAdapter mRestAdapter;

    @Override
    public App init(Context context) {
        mLogger = new EmptyLogger();
        mRestAdapter = new RestAdapter(context);

        return this;
    }

    @Override
    public Logger logger() {
        return mLogger;
    }

    @Override
    public RestApi restApi() {
        return mRestAdapter;
    }
}
