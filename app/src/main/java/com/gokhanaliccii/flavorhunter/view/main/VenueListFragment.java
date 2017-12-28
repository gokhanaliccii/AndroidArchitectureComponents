package com.gokhanaliccii.flavorhunter.view.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanaliccii.flavorhunter.R;
import com.gokhanaliccii.flavorhunter.databinding.FragmentVenueListBinding;
import com.gokhanaliccii.flavorhunter.view.BaseFragment;
import com.gokhanaliccii.flavorhunter.view.main.adapter.VenueListAdapter;
import com.gokhanaliccii.flavorhunter.viewmodel.ViewModelProviderFactory;

import java.util.Collections;

/**
 * Created by gokhan on 28/12/17.
 */

public class VenueListFragment extends BaseFragment {

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
        setRetainInstance(true);

        ViewModelProviderFactory viewModelProviderFactory = new ViewModelProviderFactory();
        venuesViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(VenuesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLayoutAdapter == null) {
            View view = inflater.inflate(R.layout.fragment_venue_list, container, false);
            mLayoutAdapter = FragmentVenueListBinding.bind(view);

            createListAdapter();
        }

        return mLayoutAdapter.getRoot();
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