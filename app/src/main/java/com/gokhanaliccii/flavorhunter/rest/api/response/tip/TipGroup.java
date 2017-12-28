package com.gokhanaliccii.flavorhunter.rest.api.response.tip;

import com.gokhanaliccii.flavorhunter.rest.api.response.user.UserComment;

import java.util.ArrayList;

/**
 * Created by gokhan on 28/12/17.
 */

public class TipGroup {
    String type;
    String name;
    int count;
    ArrayList<UserComment> items;

}
