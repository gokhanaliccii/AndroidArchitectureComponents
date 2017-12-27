package com.gokhanaliccii.flavorhunter.rest.interceptor;

import android.content.Context;

import com.gokhanaliccii.flavorhunter.R;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gokhan on 28/12/17.
 */

public class FoursquareInterceptor implements Interceptor {

    final static String CLIENT_ID = "client_id";
    final static String CLIENT_SECRET = "client_secret";
    final static String FOURSQUARE_VERSION = "v";

    private final String mClientId;
    private final String mClientSecret;
    private final String mApiVersion;

    public FoursquareInterceptor(Context context) {
        this.mClientId = context.getString(R.string.foursquare_client_id);
        this.mClientSecret = context.getString(R.string.foursquare_client_secret);
        this.mApiVersion = context.getString(R.string.foursquare_api_version);
    }

    public FoursquareInterceptor(String mClientId, String mClientSecret, String mApiVersion) {
        this.mClientId = mClientId;
        this.mClientSecret = mClientSecret;
        this.mApiVersion = mApiVersion;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl modifiedUrl = request.url().newBuilder()
                .addQueryParameter(CLIENT_ID, mClientId)
                .addQueryParameter(CLIENT_SECRET, mClientSecret)
                .addQueryParameter(FOURSQUARE_VERSION, mApiVersion)
                .build();

        request = request.newBuilder().url(modifiedUrl).build();

        return chain.proceed(request);
    }
}
