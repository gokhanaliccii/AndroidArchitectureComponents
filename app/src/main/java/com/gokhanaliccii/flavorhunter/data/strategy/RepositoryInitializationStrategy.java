package com.gokhanaliccii.flavorhunter.data.strategy;

import com.gokhanaliccii.flavorhunter.components.repository.Repository;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;

import java.util.List;

/**
 * Created by gokhan on 28/12/17.
 */

public interface RepositoryInitializationStrategy {

    void initialize();

    Repository<Venue> venueRepository();

    Repository<List<Venue>> venueListRepository();
}
