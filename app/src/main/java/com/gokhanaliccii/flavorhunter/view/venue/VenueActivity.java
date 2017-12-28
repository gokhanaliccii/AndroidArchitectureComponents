package com.gokhanaliccii.flavorhunter.view.venue;

import android.os.Bundle;
import android.util.Log;

import com.gokhanaliccii.flavorhunter.R;
import com.gokhanaliccii.flavorhunter.view.BaseActivity;
import com.gokhanaliccii.flavorhunter.view.venue.list.VenueListFragment;
import com.gokhanaliccii.flavorhunter.view.venue.router.IVenueRouter;
import com.gokhanaliccii.flavorhunter.view.venue.router.VenueDetailRouter;

public class VenueActivity extends BaseActivity {

    private static final String TAG = VenueActivity.class.getName();

    private boolean hasTwoPane;

    public IVenueRouter venueRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasTwoPane = getResources().getBoolean(R.bool.has_two_pane);

        if (savedInstanceState == null) {

            venueRouter = new VenueDetailRouter(this, hasTwoPane);
            fragmentTransaction().replace(R.id.container_list, VenueListFragment.newInstance())
                    .commit();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        int backStackEntryCount = fragmentManager().getBackStackEntryCount();
        Log.i(TAG, "onResume: count"+ backStackEntryCount);
    }

    public IVenueRouter getVenueRouter() {
        return venueRouter;
    }
}