package com.gokhanaliccii.flavorhunter.rest.api.response.venue;

import com.gokhanaliccii.flavorhunter.rest.api.response.tip.Tips;

import static com.gokhanaliccii.flavorhunter.util.NullChecker.isNull;

/**
 * Created by gokhan on 28/12/17.
 */

public class Venue {

    private String id;
    private String name;
    private float rating;
    private VenueLocation location;
    private Tips tips;
    private Photo bestPhoto;
    private Phone contact;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public VenueLocation getLocation() {
        return location;
    }

    public String getAddress(){
        return location.getAddress();
    }

    public String getImageUrl(){
        if(bestPhoto!= null){
            return bestPhoto.getPhotoUrl();
        }

        return null;
    }

    public String getContactNumber(){
        if(!isNull(contact)){
            return contact.getPhone();
        }

        return null;
    }
}
