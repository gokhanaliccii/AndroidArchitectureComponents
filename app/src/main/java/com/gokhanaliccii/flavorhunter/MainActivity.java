package com.gokhanaliccii.flavorhunter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gokhanaliccii.flavorhunter.components.repository.callback.DataLoadListener;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "§";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlavorHunterApp.repositoryFactory().getVenueListRepositoy().get(new DataLoadListener<List<Venue>>() {
            @Override
            public void onDataLoaded(List<Venue> venues) {
                Log.i(TAG, "onDataLoaded: " + venues.size());
            }

            @Override
            public void onDataLoadFailed(String reason) {
                Log.i(TAG, "onDataLoadFailed: " + reason);
            }
        }, () -> "kafe", () -> "izmit");


    }
}
