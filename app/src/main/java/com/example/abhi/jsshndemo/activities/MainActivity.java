package com.example.abhi.jsshndemo.activities;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.fragments.ContactFragment;
import com.example.abhi.jsshndemo.fragments.EventsFragment;
import com.example.abhi.jsshndemo.fragments.HomeFragment;
import com.example.abhi.jsshndemo.fragments.RegisterFragment;
import com.example.abhi.jsshndemo.fragments.ScheduleFragment;
import com.example.abhi.jsshndemo.fragments.SponsorsFragment;
import com.example.abhi.jsshndemo.fragments.TeamFragment;
import com.example.abhi.jsshndemo.menu.DrawerAdapter;
import com.example.abhi.jsshndemo.menu.DrawerItem;
import com.example.abhi.jsshndemo.menu.SimpleItem;
import com.example.abhi.jsshndemo.menu.SpaceItem;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.lang.reflect.Field;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    private String TAG;
    private static final int POS_HOME = 0;
    private static final int POS_EVENTS = 1;
    private static final int POS_SCHEDULE = 2;
    private static final int POS_CONTACT = 3;
    private static final int POS_SPONSORS = 4;
    private static final int POS_TEAM = 5;
    private static final int POS_EXIT = 6;
    private int POS_CURRENT=-1;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;
    private BottomNavigationView mBottomNav;

    private Fragment selectedFragment=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG = getClass().getSimpleName();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initBottomNavigation();

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_HOME).setChecked(true),
                createItemFor(POS_EVENTS),
                createItemFor(POS_SCHEDULE),
                createItemFor(POS_CONTACT),
                createItemFor(POS_SPONSORS),
                createItemFor(POS_TEAM),
                //new SpaceItem(48),
                createItemFor(POS_EXIT)));
        adapter.setListener((DrawerAdapter.OnItemSelectedListener) this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_HOME);
        //showFragment(HomeFragment.newInstance(),0);


    }


    @Override
    public void onItemSelected(int position) {
        if (position == POS_EXIT) {
            POS_CURRENT=6;
            finish();
        }
        slidingRootNav.closeMenu();
        //Fragment nselectedFragment=null;
        switch (position){
            case POS_HOME:
                selectedFragment= HomeFragment.newInstance();
                Log.v(TAG,"Home Fragment Selected");
                break;
            case POS_EVENTS:
                selectedFragment=EventsFragment.newInstance();
                Log.v(TAG,"Event Fragment Selected");
                break;
            case POS_SCHEDULE:
                selectedFragment= ScheduleFragment.newInstance();
                Log.v(TAG,"Schedule Fragment Selected");
                break;
            case POS_CONTACT:
                selectedFragment= ContactFragment.newInstance();
                Log.v(TAG,"Contact Fragment Selected");
                break;
            case POS_SPONSORS:
                selectedFragment= SponsorsFragment.newInstance();
                Log.v(TAG,"Sponsors Fragment Selected");
                break;
            case POS_TEAM:
                selectedFragment = TeamFragment.newInstance();
                Log.v(TAG,"Team Fragment Selected");
                break;

        }
        if(position!=POS_EXIT){
            showFragment(selectedFragment,position);

        }
       // Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
        //showFragment(selectedScreen);
    }

    private void showFragment(Fragment fragment,int position) {
        //Fragment current= getFragmentManager().findFragmentById(R.id.frame_layout);
        if(POS_CURRENT!=position){
        POS_CURRENT=position;
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
        }
        else
        Log.v(TAG,"same fragment selected");
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorPrimary))
                .withSelectedIconTint(color(R.color.colorAccent))
                .withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }


    /**
     * Bottom Navoigation Implementation
     */
    public void initBottomNavigation(){
        mBottomNav=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Fragment selectedFragment=null;
                int position=0;
                switch (item.getItemId()){
                    case R.id.home:
                        position=0;
                        selectedFragment= HomeFragment.newInstance();
                        Log.v(TAG,"Home Fragment Selected");
                        break;
                    case R.id.events:
                        position=1;
                        selectedFragment=EventsFragment.newInstance();
                        Log.v(TAG,"Event Fragment Selected");
                        break;
                    case R.id.schedule:
                        position=2;
                        selectedFragment= ScheduleFragment.newInstance();
                        Log.v(TAG,"Schedule Fragment Selected");
                        break;
                    case R.id.register:
                        position=7;
                        selectedFragment= RegisterFragment.newInstance();
                        Log.v(TAG,"Register Fragment Selected");
                        break;
                }
                showFragment(selectedFragment,position);
                /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                */
                return true;
            }
        });
        //Manually displaying the first fragment - one time only
        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment
                .newInstance());
        transaction.commit();*/
        showFragment(HomeFragment.newInstance(),0);
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

