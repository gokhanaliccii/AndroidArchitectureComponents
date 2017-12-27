package com.gokhanaliccii.flavorhunter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gokhanaliccii.flavorhunter.rest.RestAdapter;
import com.gokhanaliccii.flavorhunter.rest.api.response.venue.Venue;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "§";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestAdapter.getsInstance().getSearchApi()
                .getVenuesByNearby("kafe", "izmit")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(venueListResponse -> {

            List<Venue> venues = venueListResponse.getResponse().getVenues();
            Log.i(TAG, "onCreate: "+venues.size());

        });
    }
}
