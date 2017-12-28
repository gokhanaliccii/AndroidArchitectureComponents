package com.gokhanaliccii.flavorhunter.variant.mock;

import android.content.Context;

import com.gokhanaliccii.flavorhunter.rest.api.RestApi;
import com.gokhanaliccii.flavorhunter.rest.api.service.PlaceApi;

/**
 * Created by gokhan on 28/12/17.
 */

public class MockRestAdapter implements RestApi {

    private MockPlaceApi mPlaceApi;

    public MockRestAdapter(Context context) {
        mPlaceApi = new MockPlaceApi(context);
    }

    @Override
    public PlaceApi placeApi() {
        return mPlaceApi;
    }
}
