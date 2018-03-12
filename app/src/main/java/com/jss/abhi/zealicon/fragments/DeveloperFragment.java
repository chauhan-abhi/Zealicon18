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
import com.jss.abhi.zealicon.recyclerview.adapters.DeveloperAdapter;
import java.util.ArrayList;

public class DeveloperFragment extends Fragment {

    RecyclerView devRecyclerView;
    ArrayList<Developer> developerArrayList;

    public static Fragment newInstance() {
        DeveloperFragment fragment = new DeveloperFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDeveloperData();
    }
    public void initDeveloperData(){
        developerArrayList = new ArrayList<>();
        developerArrayList.add(new Developer("Abhishek Singhania","mrsinghania",
                    "4th yr CSE","9643156229"));
        developerArrayList.add(new Developer("Abhijeet","abhijeet",
                "3rd yr CSE","9990649248"));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_developer, container, false);
        devRecyclerView = view.findViewById(R.id.developer_rv);
        devRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        devRecyclerView.setAdapter(new DeveloperAdapter(developerArrayList));
        return view;
    }


}