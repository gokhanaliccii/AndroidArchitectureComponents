package com.gokhanaliccii.flavorhunter.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.gokhanaliccii.flavorhunter.R;

/**
 * Created by gokhan on 27/12/17.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";


    public FragmentManager fragmentManager() {
        return getSupportFragmentManager();
    }

    public FragmentTransaction fragmentTransaction() {
        return fragmentManager().beginTransaction();
    }

    public FragmentTransaction animatedFragmentTransaction() {
        FragmentTransaction fragmentTransaction = fragmentTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_trans_to_screen_from_right_outside,
                R.anim.fragment_trans_to_left_outside_from_screen,
                R.anim.fragment_trans_to_screen_from_left_outside,
                R.anim.fragment_trans_to_right_outside_from_screen);

        return fragmentTransaction;
    }

}