package com.gokhanaliccii.flavorhunter.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.gokhanaliccii.flavorhunter.FlavorHunterApp;
import com.gokhanaliccii.flavorhunter.data.RepositoryFactory;
import com.gokhanaliccii.flavorhunter.view.main.VenuesViewModel;

/**
 * Created by gokhan on 28/12/17.
 */

public class ViewModelProviderFactory implements ViewModelProvider.Factory {


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        RepositoryFactory repositoryFactory = FlavorHunterApp.repositoryFactory();

        if (modelClass.isAssignableFrom(VenuesViewModel.class)) {
            VenuesViewModel venuesViewModel = new VenuesViewModel(repositoryFactory.getVenueListRepositoy());

            return (T) venuesViewModel;
        }


        return null;
    }
}