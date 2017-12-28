package com.gokhanaliccii.flavorhunter;

import android.os.Bundle;

import com.gokhanaliccii.flavorhunter.view.BaseActivity;
import com.gokhanaliccii.flavorhunter.view.main.VenueListFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = "§";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {

            fragmentTransaction().add(R.id.container_list, VenueListFragment.newInstance(), VenueListFragment.TAG).commit();
        }

    }
}