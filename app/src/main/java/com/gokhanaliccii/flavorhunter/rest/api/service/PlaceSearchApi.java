package com.gokhanaliccii.flavorhunter.rest.api.service;

import com.gokhanaliccii.flavorhunter.rest.api.response.base.FoursquareResponse;
import com.gokhanaliccii.flavorhunter.rest.api.response.venuelist.VenueListResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gokhan on 28/12/17.
 */

public interface PlaceSearchApi {

    @GET("search")
    Observable<FoursquareResponse<VenueListResponse>> getVenuesByNearby(@Query("query") String placeType, @Query("near") String near);

    @GET("search")
    Observable<FoursquareResponse<VenueListResponse>> getVenuesByLocation(@Query("query") String placeType, @Query("ll") String latLng);

}
