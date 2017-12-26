package com.gokhanaliccii.flavorhunter.components.permission;

/**
 * Created by gokhan on 27/12/17.
 */

public interface PermissionRequester {

    void requestPermissions(PermissionResponseListener responseListener, String[] permissions);

    boolean shouldShowRequestPermissionRationale(String permission);
}
