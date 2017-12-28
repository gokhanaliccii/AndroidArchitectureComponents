package com.gokhanaliccii.flavorhunter.data.venue;

import com.gokhanaliccii.flavorhunter.components.repository.Repository;
import com.gokhanaliccii.flavorhunter.components.repository.callback.DataLoadListener;
import com.gokhanaliccii.flavorhunter.components.repository.callback.TaskCompleteListener;
import com.gokhanaliccii.flavorhunter.components.repository.criteria.SearchCriteria;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.VenueDetailResponse;
import com.gokhanaliccii.flavorhunter.rest.api.service.PlaceApi;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.gokhanaliccii.flavorhunter.util.NullChecker.isNull;

/**
 * Created by gokhan on 28/12/17.
 */

public class VenueDetailRemoteRepository implements Repository<Venue> {

    private PlaceApi placeApi;

    public VenueDetailRemoteRepository(PlaceApi placeApi) {
        this.placeApi = placeApi;
    }

    @Override
    public void get(DataLoadListener<Venue> listener, SearchCriteria... searchCriteria) {
        if ((isNull(listener) || isNull(searchCriteria))) {
            throw new IllegalArgumentException("DataLoadListener or SearchCriteria cant be null");
        }

        placeApi.getVenueDetail(searchCriteria[0].criteria()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fourSquareResponse -> {
                    VenueDetailResponse venueDetailResponse = fourSquareResponse.getResponse();
                    Venue venue = venueDetailResponse.getVenue();

                    listener.onDataLoaded(venue);
                }, error -> {
                    // TODO: 28/12/17
                    listener.onDataLoadFailed("todo...");
                });
    }

    @Override
    public void remove(TaskCompleteListener listener, SearchCriteria... searchCriteria) {
        throw new RuntimeException("not implemented yet");
    }

}