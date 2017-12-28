package com.gokhanaliccii.flavorhunter.view.venue.detail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.gokhanaliccii.flavorhunter.components.repository.Repository;
import com.gokhanaliccii.flavorhunter.components.repository.callback.DataLoadListener;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;

/**
 * Created by gokhan on 28/12/17.
 */

public class VenueDetailViewModel extends ViewModel {

    private Repository<Venue> mVenueRepository;

    public MutableLiveData<Venue> mVenue = new MutableLiveData<>();

    public VenueDetailViewModel(Repository<Venue> mVenueRepository) {
        this.mVenueRepository = mVenueRepository;
    }

    public void loadVenueDetail(String venueId) {
        mVenueRepository.get(new DataLoadListener<Venue>() {
            @Override
            public void onDataLoaded(Venue venue) {
                mVenue.setValue(venue);
            }

            @Override
            public void onDataLoadFailed(String reason) {

            }
        }, () -> venueId);
    }


}
