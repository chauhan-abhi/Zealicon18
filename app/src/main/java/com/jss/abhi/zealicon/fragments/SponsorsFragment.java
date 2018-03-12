package com.jss.abhi.zealicon.fragments;


import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.model.Developer;
import com.jss.abhi.zealicon.recyclerview.adapters.SponsorAdapter;
import java.util.ArrayList;

public class SponsorsFragment extends Fragment {

    private RecyclerView sponsorRecyclerView;
    private ArrayList<Developer> mSponsorList;
    String[] nameArray=new String[]{
            "Corporation Bank",
            "IES Master",
            "GateForum",
            "Unique Shiksha",
            "Hello Intern",
            "DU Khabar",
            "DU Express",
            "DU Beat",
            "The Education Tree",
            "xoxoday",
            "i3India Technologies",
            "Smaash",
            "KTM",
            "CETPA",
            "Coding Blocks"

    };
    String[] catArray=new String[]{
            "Title Sponsor",
            "Education Sponsor",
            "Education Sponsor",
            "Education Sponsor",
            "Media Sponsor",
            "Media Sponsor",
            "Media Sponsor",
            "Media Sponsor",
            "Media Sponsor",
            "Experience Partner",
            "Hacking Partner",
            "Gaming Partner",
            "Biking Partner",
            "Training Partner",
            "Technical Partner"
    };
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
        for(int i=0;i<nameArray.length;i++){
            mSponsorList.add(new Developer(nameArray[i],"",catArray[i]));
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