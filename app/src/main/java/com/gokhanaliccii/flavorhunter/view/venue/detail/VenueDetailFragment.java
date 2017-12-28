package com.gokhanaliccii.flavorhunter.view.venue.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gokhanaliccii.flavorhunter.R;
import com.gokhanaliccii.flavorhunter.databinding.FragmentVenueDetailBinding;
import com.gokhanaliccii.flavorhunter.view.BaseBindableFragment;
import com.gokhanaliccii.flavorhunter.viewmodel.ViewModelProviderFactory;

/**
 * Created by gokhan on 28/12/17.
 */

public class VenueDetailFragment extends BaseBindableFragment<FragmentVenueDetailBinding> {

    public static final String TAG = VenueDetailFragment.class.getName();

    public static final String ARG_VENUE_ID = "venueId";

    private VenueDetailViewModel viewModel;
    private String currentVenueId;

    public static VenueDetailFragment newInstance(String venueId) {
        Bundle args = new Bundle();
        args.putString(ARG_VENUE_ID, venueId);

        VenueDetailFragment fragment = new VenueDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentVenueId = getArguments().getString(ARG_VENUE_ID, null);

        ViewModelProviderFactory viewModelProviderFactory = new ViewModelProviderFactory();
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(VenueDetailViewModel.class);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.loadVenueDetail(currentVenueId);
    }

    @Override
    protected void onViewInflated() {
        viewModel.mVenue.observe(this, venue -> {
            mLayoutAdapter.setVenue(venue);
            mLayoutAdapter.executePendingBindings();
        });
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_venue_detail;
    }
}