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
import com.example.abhi.zealicon.recyclerview.adapters.TeamAdapter;
import java.util.ArrayList;

public class TeamFragment extends Fragment {

    private RecyclerView mTeamRecycler;
    private ArrayList<Developer> mTeamList;

    public static Fragment newInstance() {
        TeamFragment fragment = new TeamFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTeamData();
    }

    private void initTeamData() {
        mTeamList = new ArrayList<>();
        for(int i=1;i<5;i++){
            //used Developer model as for zealicon team member
            mTeamList.add(new Developer("DV","https://scontent-bom1-1.xx.fbcdn.net/v/t31.0-8/17637144_1270070083109005_6740293189372331361_o.jpg?oh=5b4076ba66d30d85f859881624a96ecd&oe=5B086293","CTC","9999999999"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_team, container, false);
        mTeamRecycler = view.findViewById(R.id.team_rv);
        mTeamRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTeamRecycler.setAdapter(new TeamAdapter(mTeamList));
        return view;
    }


}