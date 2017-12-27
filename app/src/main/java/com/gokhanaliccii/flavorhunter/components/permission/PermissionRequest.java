package com.gokhanaliccii.flavorhunter.components.permission;

/**
 * Created by gokhan on 27/12/17.
 */

public class PermissionRequest {

    private PermissionResponseListener mResponseListener;
    private String[] mPermissions;

    public PermissionRequest(PermissionResponseListener mResponseListener, String[] permissions) {
        this.mResponseListener = mResponseListener;
        this.mPermissions = permissions;
    }

    public PermissionResponseListener getResponseListener() {
        return mResponseListener;
    }

    public String[] getPermissions() {
        return mPermissions;
    }
}
