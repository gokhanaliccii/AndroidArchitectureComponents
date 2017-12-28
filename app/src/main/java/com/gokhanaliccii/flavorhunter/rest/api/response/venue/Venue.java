package com.gokhanaliccii.flavorhunter.rest.api.response.venue;

import com.gokhanaliccii.flavorhunter.rest.api.response.tip.Tips;

/**
 * Created by gokhan on 28/12/17.
 */

public class Venue {

    private String id;
    private String name;
    private float rating;
    private VenueLocation location;
    private Tips tips;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }
}
