package com.gokhanaliccii.flavorhunter.view.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.gokhanaliccii.flavorhunter.components.repository.Repository;
import com.gokhanaliccii.flavorhunter.components.repository.callback.DataLoadListener;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;

import java.util.List;

/**
 * Created by gokhan on 28/12/17.
 */

public class VenuesViewModel extends ViewModel {

    public MutableLiveData<List<Venue>> venuesList = new MutableLiveData<>();

    private Repository<List<Venue>> venueRepository;

    public VenuesViewModel(Repository<List<Venue>> venueRepository) {
        this.venueRepository = venueRepository;
    }

    public void loadVenues(String place, String searchedPlaceType) {
        venueRepository.get(new DataLoadListener<List<Venue>>() {
            @Override
            public void onDataLoaded(List<Venue> venues) {
                venuesList.setValue(venues);
            }

            @Override
            public void onDataLoadFailed(String reason) {

            }
        }, () -> place, () -> searchedPlaceType);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}