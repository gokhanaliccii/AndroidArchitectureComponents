package com.gokhanaliccii.flavorhunter.components.permission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gokhanaliccii.flavorhunter.util.CollectionUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gokhan on 27/12/17.
 */

public class PermissionActivity extends AppCompatActivity implements PermissionRequester {

    private static final String TAG = "BaseActivity";

    private static WeakHashMap<Integer, PermissionResponseListener> sPermissionResponseListenerMap = new WeakHashMap<>();
    private static AtomicInteger sRequesterId = new AtomicInteger(1);

    public static void requestForPermission(Context context, PermissionResponseListener responseListener, String[] permissions) {
        Intent permissionRequestIntent = new Intent(context, PermissionActivity.class);
        permissionRequestIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_NO_ANIMATION);

        context.startActivity(permissionRequestIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void requestPermissions(PermissionResponseListener responseListener, String[] permissions) {
        if (responseListener != null) {

            if (sdkAfterMarshmallow() && hasUnGrantedPermission(permissions)) {
                int newRequesterId = storeResponseListener(responseListener);
                findAndRequestForUnGrantedPermissions(newRequesterId, permissions);
            } else {
                responseListener.onPermissionGranted();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionResponseListener permissionResponseListener = sPermissionResponseListenerMap.get(requestCode);
        if (permissionResponseListener == null) {
            Log.e(TAG, "onRequestPermissionsResult: responseListener not found for :" + requestCode);
            return;
        }

        if (hasUnGrantedPermission(permissions)) {
            permissionResponseListener.onPermissionRejected();
        } else {
            permissionResponseListener.onPermissionGranted();
        }

        clearResponseListener(requestCode);
    }

    private int createNewRequesterId() {
        return sRequesterId.incrementAndGet();
    }

    private int storeResponseListener(PermissionResponseListener responseListener) {
        int newRequesterId = createNewRequesterId();
        sPermissionResponseListenerMap.put(newRequesterId, responseListener);

        return newRequesterId;
    }

    private void clearResponseListener(int requesterId) {
        if (sPermissionResponseListenerMap.containsKey(requesterId)) {
            sPermissionResponseListenerMap.remove(requesterId);
        }
    }

    private void findAndRequestForUnGrantedPermissions(int requesterId, String[] permissions) {
        String[] unGrantedPermissions = findUnGrantedPermissions(permissions);
        requestPermissions(unGrantedPermissions, requesterId);
    }

    private String[] findUnGrantedPermissions(String[] permissions) {
        if (CollectionUtil.isEmpty(permissions)) {
            return new String[0];
        }

        List<String> unGrantedPermissions = new LinkedList<>();

        for (String permission : permissions) {
            if (permission == null || isGrantedAlready(permission)) {
                continue;
            }

            unGrantedPermissions.add(permission);
        }


        return (String[]) unGrantedPermissions.toArray();
    }

    private boolean isGrantedAlready(String permission) {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean hasUnGrantedPermission(String[] permissions) {
        String[] unGrantedPermissions = findUnGrantedPermissions(permissions);
        return CollectionUtil.hasItem(unGrantedPermissions);
    }

    private boolean sdkAfterMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    @Override
    protected void onDestroy() {
        sPermissionResponseListenerMap.clear();
        super.onDestroy();
    }
}
