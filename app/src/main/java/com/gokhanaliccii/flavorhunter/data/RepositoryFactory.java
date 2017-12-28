package com.gokhanaliccii.flavorhunter.data;

import com.gokhanaliccii.flavorhunter.components.repository.Repository;
import com.gokhanaliccii.flavorhunter.data.strategy.RepositoryInitializationStrategy;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;

import java.util.List;

/**
 * Created by gokhan on 28/12/17.
 */

public class RepositoryFactory {

    private Repository<Venue> mVenueRepository;
    private Repository<List<Venue>> mVenueListRepository;

    public RepositoryFactory(RepositoryInitializationStrategy strategy) {
        strategy.initialize();

        mVenueRepository = strategy.venueRepository();
        mVenueListRepository = strategy.venueListRepository();
    }

    public Repository<Venue> getVenueDetailRepositoy() {
        return mVenueRepository;
    }

    public Repository<List<Venue>> getVenueListRepositoy() {
        return mVenueListRepository;
    }
}
