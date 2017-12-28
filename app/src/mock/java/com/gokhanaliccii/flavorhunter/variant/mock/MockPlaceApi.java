package com.gokhanaliccii.flavorhunter.variant.mock;

import android.content.Context;

import com.gokhanaliccii.flavorhunter.rest.api.response.base.FoursquareResponse;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.VenueDetailResponse;
import com.gokhanaliccii.flavorhunter.rest.api.response.venuelist.VenueListResponse;
import com.gokhanaliccii.flavorhunter.rest.api.service.PlaceApi;
import com.gokhanaliccii.flavorhunter.util.AssetReader;

import rx.Observable;

/**
 * Created by gokhan on 28/12/17.
 */

public class MockPlaceApi implements PlaceApi {

    public static final String MOCK_VENUES_RESPONSE_JSON = "mock/venues_response.json";
    public static final String MOCK_VENUE_DETAIL_RESPONSE_JSON = "mock/venue_detail_response.json";

    private Context context;

    public MockPlaceApi(Context context) {
        this.context = context;
    }

    @Override
    public Observable<FoursquareResponse<VenueListResponse>> getVenuesByNearby(String placeType, String near) {
        return Observable.just(MOCK_VENUES_RESPONSE_JSON)
                .map(filePath -> {
                    Class<? extends FoursquareResponse> targetClazz = new FoursquareResponse<VenueListResponse>().getClass();

                    return (FoursquareResponse<VenueListResponse>) AssetReader.newReader(context, targetClazz)
                            .path(filePath)
                            .map();

                }).switchMap(response -> Observable.just(response));
    }

    @Override
    public Observable<FoursquareResponse<VenueListResponse>> getVenuesByLocation(String placeType, String latLng) {
        return Observable.just(MOCK_VENUES_RESPONSE_JSON)
                .map(filePath -> {

                    Class<? extends FoursquareResponse> targetClazz = new FoursquareResponse<VenueListResponse>().getClass();

                    return (FoursquareResponse<VenueListResponse>) AssetReader.newReader(context, targetClazz)
                            .path(filePath)
                            .map();
                }).switchMap(foursquareResponse -> Observable.just(foursquareResponse));
    }

    @Override
    public Observable<FoursquareResponse<VenueDetailResponse>> getVenueDetail(String venueId) {
        return Observable.just(MOCK_VENUE_DETAIL_RESPONSE_JSON)
                .map(filePath -> {

                    Class<? extends FoursquareResponse> targetClazz = new FoursquareResponse<VenueDetailResponse>().getClass();

                    return (FoursquareResponse<VenueDetailResponse>) AssetReader.newReader(context, targetClazz)
                            .path(filePath).map();
                }).switchMap(foursquareResponse -> Observable.just(foursquareResponse));
    }
}
