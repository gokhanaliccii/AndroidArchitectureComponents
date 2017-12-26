package com.gokhanaliccii.flavorhunter.components.permission;

/**
 * Created by gokhan on 27/12/17.
 */

public interface PermissionResponseListener {

    void onPermissionGranted();

    void onPermissionRejected();

}