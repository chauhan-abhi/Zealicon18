package com.jss.abhi.zealicon.recyclerview.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;

/**
 * Created by abhi on 9/2/18.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles={"Team","Developers","Sponsors","About"};

    public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }


}
