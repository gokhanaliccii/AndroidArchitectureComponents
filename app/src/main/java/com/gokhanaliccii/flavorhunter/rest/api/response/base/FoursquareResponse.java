package com.gokhanaliccii.flavorhunter.rest.api.response.base;

/**
 * Created by gokhan on 28/12/17.
 */

public class FoursquareResponse<T> {

    private Meta meta;
    private T response;

    public T getResponse() {
        return response;
    }
}
