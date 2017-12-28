package com.gokhanaliccii.flavorhunter.variant;

import android.content.Context;

import com.gokhanaliccii.flavorhunter.components.log.Logger;
import com.gokhanaliccii.flavorhunter.rest.api.RestApi;

/**
 * Created by gokhan on 28/12/17.
 */

public class ApplicationVariant implements App {

    @Override
    public App init(Context context) {

        return this;
    }

    @Override
    public Logger logger() {
        return null;
    }

    @Override
    public RestApi restApi() {
        return null;
    }
}
