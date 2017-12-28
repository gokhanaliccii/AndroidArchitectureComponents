package com.gokhanaliccii.flavorhunter.rest.api.response.venue;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gokhan on 28/12/17.
 */

public class Photo {
    public static final String DEFAULT_SIZE = "300";
    @SerializedName("prefix")
    private String prefix;

    @SerializedName("suffix")
    private String suffix;

    public String getPhotoUrl() {
        return prefix + DEFAULT_SIZE + suffix;
    }

    public String getPhotoUrl(String size) {
        return prefix + size + suffix;
    }
}
