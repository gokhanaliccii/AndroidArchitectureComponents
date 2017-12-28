package com.gokhanaliccii.flavorhunter.data.venue;

import com.gokhanaliccii.flavorhunter.components.repository.Repository;
import com.gokhanaliccii.flavorhunter.components.repository.callback.DataLoadListener;
import com.gokhanaliccii.flavorhunter.components.repository.callback.TaskCompleteListener;
import com.gokhanaliccii.flavorhunter.components.repository.criteria.SearchCriteria;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;
import com.gokhanaliccii.flavorhunter.rest.api.response.venuelist.VenueListResponse;
import com.gokhanaliccii.flavorhunter.rest.api.service.PlaceApi;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.gokhanaliccii.flavorhunter.util.NullChecker.isNull;

/**
 * Created by gokhan on 28/12/17.
 */

public class VenuesRemoteRepository implements Repository<List<Venue>> {

    private PlaceApi placeApi;

    public VenuesRemoteRepository(PlaceApi placeApi) {
        this.placeApi = placeApi;
    }

    @Override
    public void get(DataLoadListener<List<Venue>> listener, SearchCriteria... searchCriteria) {
        if ((isNull(listener) || isNull(searchCriteria))) {
            throw new IllegalArgumentException("DataLoadListener or SearchCriteria cant be null");
        }

        placeApi.getVenuesByNearby(searchCriteria[0].criteria(), searchCriteria[1].criteria())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(foursquareResponse -> {
                    VenueListResponse response = foursquareResponse.getResponse();
                    List<Venue> venues = response.getVenues();

                    listener.onDataLoaded(venues);
                }, error -> {

                });

    }

    @Override
    public void remove(TaskCompleteListener listener, SearchCriteria[] searchCriteria) {
        throw new RuntimeException("not implemented yet");
    }

}