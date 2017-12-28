package com.gokhanaliccii.flavorhunter.view.venue.router;

import com.gokhanaliccii.flavorhunter.R;
import com.gokhanaliccii.flavorhunter.view.BaseActivity;
import com.gokhanaliccii.flavorhunter.view.venue.detail.VenueDetailFragment;

/**
 * Created by gokhan on 28/12/17.
 */

public class VenueDetailRouter implements IVenueRouter {

    private BaseActivity mActivity;
    private boolean hasTwoPane;

    public VenueDetailRouter(BaseActivity mActivity, boolean hasTwoPane) {
        this.mActivity = mActivity;
        this.hasTwoPane = hasTwoPane;
    }

    @Override
    public void openVenue(String venueId) {
        if (hasTwoPane) {

        } else {
            mActivity.animatedFragmentTransaction().replace(R.id.container_list,
                    VenueDetailFragment.newInstance(venueId)).addToBackStack(VenueDetailFragment.TAG).commit();
        }


    }


}
