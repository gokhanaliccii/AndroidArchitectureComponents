package com.gokhanaliccii.flavorhunter.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gokhan on 28/12/17.
 */

public abstract class BaseFragment<ViewAdapter extends ViewDataBinding> extends Fragment {

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

        return mLayoutAdapter.getRoot();
    }


    protected abstract int layoutRes();

    protected abstract void onViewInflated();

}
