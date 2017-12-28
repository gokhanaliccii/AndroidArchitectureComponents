package com.gokhanaliccii.flavorhunter.variant;

import android.content.Context;

import com.gokhanaliccii.flavorhunter.components.log.Logger;
import com.gokhanaliccii.flavorhunter.rest.api.RestApi;
import com.gokhanaliccii.flavorhunter.components.log.ConsoleLogger;
import com.gokhanaliccii.flavorhunter.variant.mock.MockRestAdapter;

/**
 * Created by gokhan on 28/12/17.
 */

public class ApplicationVariant implements App {

    private MockRestAdapter mRestAdapter;
    private ConsoleLogger mConsoleAdapter;

    @Override
    public App init(Context context) {
        mRestAdapter = new MockRestAdapter(context);
        mConsoleAdapter = new ConsoleLogger();

        return this;
    }

    @Override
    public Logger logger() {
        return mConsoleAdapter;
    }

    @Override
    public RestApi restApi() {
        return mRestAdapter;
    }
}
