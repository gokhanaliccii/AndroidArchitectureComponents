package com.gokhanaliccii.flavorhunter.components.action.call;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.gokhanaliccii.flavorhunter.components.action.IAction;
import com.gokhanaliccii.flavorhunter.components.permission.PermissionRequester;
import com.gokhanaliccii.flavorhunter.components.permission.PermissionResponseListener;

/**
 * Created by gokhan on 28/12/17.
 */

// TODO: 28/12/17 show will call phone number info related message before call action
public class CallAction implements IAction, PermissionResponseListener {

    private Activity activity;
    private String phoneNo;
    private PermissionRequester permissionRequester;

    public CallAction(Activity activity, PermissionRequester permissionRequester, String phoneNo) {
        this.activity = activity;
        this.permissionRequester = permissionRequester;
        this.phoneNo = phoneNo;
    }

    @Override
    public void perform() {
        permissionRequester.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE});
    }


    @Override
    public void onPermissionGranted() {
        triggerCall();
    }

    void triggerCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNo));
        activity.startActivity(intent);
    }

    @Override
    public void onPermissionRejected() {
        // TODO: 28/12/17 show dialog
    }
}
