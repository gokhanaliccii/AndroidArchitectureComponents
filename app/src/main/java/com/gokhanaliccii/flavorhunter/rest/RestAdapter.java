package com.gokhanaliccii.flavorhunter.rest;

import android.content.Context;

import com.gokhanaliccii.flavorhunter.BuildConfig;
import com.gokhanaliccii.flavorhunter.rest.api.RestApi;
import com.gokhanaliccii.flavorhunter.rest.api.service.PlaceApi;
import com.gokhanaliccii.flavorhunter.rest.interceptor.FoursquareInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * Created by gokhan on 28/12/17.
 */

public class RestAdapter implements RestApi{

    public static final String API_URL = "https://api.foursquare.com/v2/venues/";
    private static RestAdapter sInstance;

    private Retrofit mRetrofit;
    private PlaceApi mSearchApi;


    public RestAdapter(Context context) {
        init(context);
    }

    private void init(Context context) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new FoursquareInterceptor(context));

        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(BODY));
        }

        OkHttpClient httpClient = httpClientBuilder.build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        mSearchApi = mRetrofit.create(PlaceApi.class);
    }

    @Override
    public PlaceApi placeApi() {
        return null;
    }
}
