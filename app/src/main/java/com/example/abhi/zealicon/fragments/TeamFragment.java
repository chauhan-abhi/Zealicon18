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
        mTeamList.add(new Developer("Utkarsh Singh",
                "",
                "Festival Secretary","8586852282"));
        mTeamList.add(new Developer("Abhishek Kumar",
                "",
                "Deputy Festival Secretary","9711541714"));
        mTeamList.add(new Developer("Shubham Saxena",
                "",
                "Financial Head","8287524525"));
        mTeamList.add(new Developer("Divyanshu Verma",
                "",
                "Technical and Creatives Head","9643903611"));
        mTeamList.add(new Developer("Abhishek Kumar Singh",
                "",
                "Application Head","7503024739"));
        mTeamList.add(new Developer("Ayush Sehgal",
                "",
                "Student Welfare Head","9910680524"));
        mTeamList.add(new Developer("Abhishek Trivedi",
                "",
                "Events Head","9451941865"));
        mTeamList.add(new Developer("Ayush Gupta",
                "",
                "Scheduling Head","8505959587"));
        mTeamList.add(new Developer("Gaurav Chaturvedi",
                "",
                "Sponsorship Head","9654986920"));
        mTeamList.add(new Developer("Prashant Singh",
                "",
                "Security Head","9868406005"));
        mTeamList.add(new Developer("Utkarsh Tomar",
                "",
                "Security Head","9958010964"));
        mTeamList.add(new Developer("Mimanshi Tiwari",
                "",
                "Publicity Head","9717753296"));
        mTeamList.add(new Developer("Ashwani Singh",
                "",
                "Merchandise Head","9717518241"));
        mTeamList.add(new Developer("Manu Agnihotri",
                "",
                "Merchandise Head","9560337040"));
        mTeamList.add(new Developer("Vaibhav Shukla",
                "",
                "Management Head","9818302918"));
        mTeamList.add(new Developer("Aradhya Saxena",
                "",
                "Cultural Head","9971738740"));
        mTeamList.add(new Developer("Rahul Giri",
                "",
                "Cultural Head","8447561809"));
        mTeamList.add(new Developer("Sharmishtha Sharma",
                "",
                "Marketing Head","8587998387"));
        mTeamList.add(new Developer("Abhinav Pandey",
                "",
                "Registration Head","0000000000"));
        mTeamList.add(new Developer("Aditya Talwar",
                "",
                "Registration Head","0000000000"));
        mTeamList.add(new Developer("Akash Solanki",
                "",
                "Resource Head","0000000000"));
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