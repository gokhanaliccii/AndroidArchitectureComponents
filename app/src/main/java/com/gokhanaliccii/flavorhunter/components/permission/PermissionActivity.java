package com.gokhanaliccii.flavorhunter.components.permission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

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

    private static final String ARG_PERMISSION_ID = "permission_request_id";
    public static final int NONE = -1;

    @VisibleForTesting
    static WeakHashMap<Integer, PermissionRequest> sPermissionResponseListenerMap = new WeakHashMap<>();
    @VisibleForTesting
    static AtomicInteger sRequesterId = new AtomicInteger(1);

    private int mCurrentPermissionRequestId;
    private PermissionResponseListener mPermissionResponseListener;

    public static void requestForPermission(Context context, PermissionResponseListener responseListener, String[] permissions) {
        int permissionRequestId = storePermissionRequest(responseListener, permissions);

        Intent permissionRequestIntent = new Intent(context, PermissionActivity.class);
        permissionRequestIntent.putExtra(ARG_PERMISSION_ID, permissionRequestId);
        permissionRequestIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_NO_ANIMATION);

        context.startActivity(permissionRequestIntent);
    }

    private static int storePermissionRequest(PermissionResponseListener responseListener, String[] permissions) {
        int newRequesterId = createNewRequesterId();

        PermissionRequest newPermissionRequst = new PermissionRequest(responseListener, permissions);
        sPermissionResponseListenerMap.put(newRequesterId, newPermissionRequst);

        return newRequesterId;
    }

    private static int createNewRequesterId() {
        return sRequesterId.incrementAndGet();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (hasPermissionArg()) {// TODO: 27/12/17 refactor
            mCurrentPermissionRequestId = getIntent().getIntExtra(ARG_PERMISSION_ID, NONE);

            if (!hasPermissionRequestId(mCurrentPermissionRequestId)) {
                onCompleted();
            }
        } else {
            onCompleted();
        }

        PermissionRequest permissionRequest = sPermissionResponseListenerMap.get(mCurrentPermissionRequestId);
        mPermissionResponseListener = permissionRequest.getResponseListener();

        requestPermissions(mPermissionResponseListener, permissionRequest.getPermissions());
    }

    private boolean hasPermissionArg() {
        return getIntent().hasExtra(ARG_PERMISSION_ID);
    }

    private boolean hasPermissionRequestId(int permissionRequestId) {
        return permissionRequestId != NONE;
    }


    @Override
    public void requestPermissions(PermissionResponseListener responseListener, String[] permissions) {
        if (responseListener != null) {

            if (sdkAfterMarshmallow() && hasUnGrantedPermission(permissions)) {
                findAndRequestForUnGrantedPermissions(mCurrentPermissionRequestId, permissions);
            } else {
                responseListener.onPermissionGranted();
                onCompleted();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (hasUnGrantedPermission(permissions)) {
            mPermissionResponseListener.onPermissionRejected();
        } else {
            mPermissionResponseListener.onPermissionGranted();
        }

        onCompleted();
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

        return unGrantedPermissions.toArray(new String[unGrantedPermissions.size()]);
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

    private void onCompleted() {
        finish();
    }

    private void clearReferences() {
        mPermissionResponseListener = null;
        sPermissionResponseListenerMap.clear();
    }

    @Override
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }
}
