package com.gokhanaliccii.flavorhunter.view;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gokhanaliccii.flavorhunter.components.permission.PermissionRequester;
import com.gokhanaliccii.flavorhunter.components.permission.PermissionResponseListener;
import com.gokhanaliccii.flavorhunter.util.CollectionUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gokhan on 27/12/17.
 */

public class BaseActivity extends AppCompatActivity implements PermissionRequester {

    private static final String TAG = "BaseActivity";

    private AtomicInteger mRequesterId = new AtomicInteger(1);
    private WeakHashMap<Integer, PermissionResponseListener> mPermissionResponseListenerMap = new WeakHashMap<>();

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

        PermissionResponseListener permissionResponseListener = mPermissionResponseListenerMap.get(requestCode);
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
        return mRequesterId.incrementAndGet();
    }

    private int storeResponseListener(PermissionResponseListener responseListener) {
        int newRequesterId = createNewRequesterId();
        mPermissionResponseListenerMap.put(newRequesterId, responseListener);

        return newRequesterId;
    }

    private void clearResponseListener(int requesterId) {
        if (mPermissionResponseListenerMap.containsKey(requesterId)) {
            mPermissionResponseListenerMap.remove(requesterId);
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
        mPermissionResponseListenerMap.clear();
        super.onDestroy();
    }
}