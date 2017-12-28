package com.gokhanaliccii.flavorhunter.view.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;

import java.util.List;

/**
 * Created by gokhan on 28/12/17.
 */

public class VenuesViewModel extends ViewModel {

    public LiveData<List<Venue>> venuesList = new MutableLiveData<>();

    public VenuesViewModel(LiveData<List<Venue>> venuesList) {
        this.venuesList = venuesList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}