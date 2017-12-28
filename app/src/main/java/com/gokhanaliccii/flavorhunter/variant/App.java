package com.gokhanaliccii.flavorhunter.variant;

import android.content.Context;

import com.gokhanaliccii.flavorhunter.components.log.Logger;
import com.gokhanaliccii.flavorhunter.rest.api.RestApi;

/**
 * Created by gokhan on 28/12/17.
 */

public interface App {

    App init(Context context);

    Logger logger();

    RestApi restApi();
}