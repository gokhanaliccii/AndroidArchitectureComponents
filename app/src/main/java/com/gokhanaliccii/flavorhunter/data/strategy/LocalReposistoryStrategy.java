package com.gokhanaliccii.flavorhunter.data.strategy;

import com.gokhanaliccii.flavorhunter.components.repository.Repository;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;

import java.util.List;

/**
 * Created by gokhan on 28/12/17.
 */

public class LocalReposistoryStrategy implements RepositoryInitializationStrategy {

    @Override
    public void initialize() {

    }

    @Override
    public Repository<Venue> venueRepository() {
        return null;
    }

    @Override
    public Repository<List<Venue>> venueListRepository() {
        return null;
    }
}
