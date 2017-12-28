package com.gokhanaliccii.flavorhunter.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanaliccii.flavorhunter.components.permission.PermissionActivity;
import com.gokhanaliccii.flavorhunter.components.permission.PermissionRequester;
import com.gokhanaliccii.flavorhunter.components.permission.PermissionResponseListener;

/**
 * Created by gokhan on 28/12/17.
 */

public abstract class BaseBindableFragment<ViewAdapter extends ViewDataBinding> extends Fragment implements PermissionRequester {

    protected ViewAdapter mLayoutAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(shouldRetainedBySystem());
    }

    protected boolean shouldRetainedBySystem() {
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLayoutAdapter == null) {
            View view = inflater.inflate(layoutRes(), container, false);
            mLayoutAdapter = DataBindingUtil.bind(view);
        }

        onViewInflated();

        return mLayoutAdapter.getRoot();
    }

    @Override
    public void onDestroy() {
        mLayoutAdapter = null;
        super.onDestroy();
    }

    @Override
    public void requestPermissions(PermissionResponseListener responseListener, String[] permissions) {
        PermissionActivity.requestForPermission(getContext(), responseListener, permissions);
    }

    protected abstract int layoutRes();

    protected abstract void onViewInflated();
}
