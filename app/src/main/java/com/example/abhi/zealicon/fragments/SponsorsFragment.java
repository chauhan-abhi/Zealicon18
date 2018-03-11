package com.example.abhi.zealicon.fragments;


import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhi.zealicon.R;
import com.example.abhi.zealicon.model.Developer;
import com.example.abhi.zealicon.recyclerview.adapters.SponsorAdapter;
import java.util.ArrayList;

public class SponsorsFragment extends Fragment {

    private RecyclerView sponsorRecyclerView;
    private ArrayList<Developer> mSponsorList;

    public static Fragment newInstance() {
        SponsorsFragment fragment = new SponsorsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSponsorData();

    }

    private void initSponsorData() {
        mSponsorList = new ArrayList<>();
        for(int i=0;i<10;i++){
            mSponsorList.add(new Developer("Corporation Bank","","Title Partner"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);
        sponsorRecyclerView = view.findViewById(R.id.sponsor_rv);
        sponsorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        sponsorRecyclerView.setAdapter(new SponsorAdapter(mSponsorList));
        return view;
    }


}