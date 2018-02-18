package com.example.abhi.jsshndemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.recyclerview.adapters.MyPagerAdapter;

import java.util.ArrayList;


public class InfoFragment extends Fragment {

    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragments;
    private TabLayout tabs;

    public static Fragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        return fragment;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragments= new ArrayList<>();
        initFragments();

        mViewPager = view.findViewById(R.id.pager);
        tabs = view.findViewById(R.id.tab_layout);
        mViewPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager(),mFragments));
        tabs.setupWithViewPager(mViewPager);
    }


    private void initFragments() {
        mFragments = new ArrayList<>();
        //Add fragments
        mFragments.add(TeamFragment.newInstance());
        mFragments.add(DeveloperFragment.newInstance());
        mFragments.add(SponsorsFragment.newInstance());
        mFragments.add(AboutUsFragment.newInstance());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }


}
