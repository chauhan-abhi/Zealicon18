package com.example.abhi.jsshndemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.recyclerview.adapters.MyPagerAdapter;

import java.util.ArrayList;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;


public class InfoFragment extends Fragment {

    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray, mColorArray;


    private final String[] mTitles = {"Developer", "Team", "Sponsors", "About" };

    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragments;

    public static Fragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.v("Info",mTitles.length+"");
        initFragments();

        //4 image drawables
        mImageArray = new int[]{
                R.drawable.bg_android,
                R.drawable.bg_android,
                R.drawable.bg_android,
                R.drawable.bg_android};
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_blue_light};

    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        for (String title : mTitles) {
            mFragments.add(MainFragment.getInstance(title));
            }
    }

    private void initViewPager(ViewPager mViewPager) {
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager(),mFragments,mTitles));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mCoordinatorTabLayout = view.findViewById(R.id.coordinatortablayout);
        mViewPager = view.findViewById(R.id.vp);
        mCoordinatorTabLayout.setTranslucentStatusBar(getActivity())
                .setTitle("")
                .setBackEnable(false)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mViewPager);
        initViewPager(mViewPager);

        return view;
    }


}
