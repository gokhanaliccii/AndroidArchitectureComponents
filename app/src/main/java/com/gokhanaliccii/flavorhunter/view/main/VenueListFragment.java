package com.gokhanaliccii.flavorhunter.view.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.gokhanaliccii.flavorhunter.R;
import com.gokhanaliccii.flavorhunter.databinding.FragmentVenueListBinding;
import com.gokhanaliccii.flavorhunter.view.BaseFragment;
import com.gokhanaliccii.flavorhunter.view.main.adapter.VenueListAdapter;
import com.gokhanaliccii.flavorhunter.viewmodel.ViewModelProviderFactory;

import java.util.Collections;

/**
 * Created by gokhan on 28/12/17.
 */

public class VenueListFragment extends BaseFragment<FragmentVenueListBinding> {

    public static final String TAG = VenueListFragment.class.getName();

    private FragmentVenueListBinding mLayoutAdapter;

    private VenueListAdapter venueListAdapter;

    private VenuesViewModel venuesViewModel;

    public static VenueListFragment newInstance() {
        VenueListFragment fragment = new VenueListFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProviderFactory viewModelProviderFactory = new ViewModelProviderFactory();
        venuesViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(VenuesViewModel.class);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_venue_list;
    }

    @Override
    protected void onViewInflated() {
        createListAdapter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        venuesViewModel.venuesList.observe(this, venueListAdapter::updateVenues);
        venuesViewModel.loadVenues("kocaeli", "kafe");
    }

    private void createListAdapter() {
        venueListAdapter = new VenueListAdapter(Collections.emptyList(),
                (position, venue) -> {

                });

        mLayoutAdapter.recyclerviewVenue.setLayoutManager(layoutManager());
        mLayoutAdapter.recyclerviewVenue.setAdapter(venueListAdapter);
    }

    private LinearLayoutManager layoutManager() {
        return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    }

}