package com.example.abhi.jsshndemo.fragments;

import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.model.Developer;
import com.example.abhi.jsshndemo.recyclerview.adapters.DeveloperAdapter;
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
        for(int i=0;i< 10;i++){
            developerArrayList.add(new Developer("Abhijeet","https://avatars1.githubusercontent.com/u/20797673?s=460&v=4",
                "Android Dev","https://github.com/chauhan-abhi"));
        }
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