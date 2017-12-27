package com.gokhanaliccii.flavorhunter.rest.api.response.venuelist;

import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;

import java.util.List;

/**
 * Created by gokhan on 28/12/17.
 */

public class VenueListResponse {

    String query;
    List<Venue> venues;

    public List<Venue> getVenues() {
        return venues;
    }
}
