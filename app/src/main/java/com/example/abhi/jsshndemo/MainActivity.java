package com.example.abhi.jsshndemo;


import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.abhi.jsshndemo.fragments.EventsFragment;
import com.example.abhi.jsshndemo.fragments.HomeFragment;
import com.example.abhi.jsshndemo.fragments.RegisterFragment;
import com.example.abhi.jsshndemo.fragments.ScheduleFragment;

import java.lang.reflect.Field;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private String TAG;

    private BottomNavigationView mBottomNav;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG = getClass().getSimpleName();

        initBottomNavigation();


    }

    /**
     * Bottom Navoigation Implementation
     */
    public void initBottomNavigation(){
        mBottomNav=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment=null;
                switch (item.getItemId()){
                    case R.id.home:
                        selectedFragment= HomeFragment.newInstance();
                        Log.v(TAG,"Home Fragment Selected");
                        break;
                    case R.id.events:
                        selectedFragment= EventsFragment.newInstance();
                        Log.v(TAG,"Event Fragment Selected");
                        break;
                    case R.id.schedule:
                        selectedFragment= ScheduleFragment.newInstance();
                        Log.v(TAG,"Schedule Fragment Selected");
                        break;
                    case R.id.register:
                        selectedFragment= RegisterFragment.newInstance();
                        Log.v(TAG,"Register Fragment Selected");
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });
        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment
                .newInstance());
        transaction.commit();
        disableShiftingModeOfBottomNavigationView(mBottomNav);
    }

    /**
     *  This method disables Shifting of bottom nav items in the bottom
     *  nav bar.
     *
     */

    @SuppressLint("RestrictedApi")
    private void disableShiftingModeOfBottomNavigationView(BottomNavigationView btmNavigationView) {

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) btmNavigationView.getChildAt(0);

        try {

            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {

                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                //To update view, set the checked value again
                item.setChecked(item.getItemData().isChecked());
            }


        } catch (NoSuchFieldException e) {
            e.printStackTrace();

            Log.e(TAG, "Unable to get shift mode field");


        } catch (IllegalAccessException e) {
            e.printStackTrace();

            Log.e(TAG, "Unable to change value of shift mode");

        } catch (SecurityException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}

