package com.gokhanaliccii.flavorhunter.data.strategy;

import com.gokhanaliccii.flavorhunter.components.repository.Repository;
import com.gokhanaliccii.flavorhunter.data.repository.VenueDetailRemoteRepository;
import com.gokhanaliccii.flavorhunter.data.repository.VenuesRemoteRepository;
import com.gokhanaliccii.flavorhunter.rest.api.RestApi;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;
import com.gokhanaliccii.flavorhunter.rest.api.service.PlaceApi;

import java.util.List;

/**
 * Created by gokhan on 28/12/17.
 */

public class RemoteRepositoryStrategy implements RepositoryInitializationStrategy {

    private RestApi mRestApi;

    private VenueDetailRemoteRepository mVenueRepository;
    private VenuesRemoteRepository mVenueListRepository;

    public RemoteRepositoryStrategy(RestApi restApi) {
        mRestApi = restApi;
    }

    @Override
    public void initialize() {
        PlaceApi placeApi = mRestApi.placeApi();

        mVenueRepository = new VenueDetailRemoteRepository(placeApi);
        mVenueListRepository = new VenuesRemoteRepository(placeApi);
    }

    @Override
    public Repository<Venue> venueRepository() {
        return mVenueRepository;
    }

    @Override
    public Repository<List<Venue>> venueListRepository() {
        return mVenueListRepository;
    }
}
