package com.example.abhi.jsshndemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.recyclerview.adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhi on 9/2/18.
 */

public class MainFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NestedScrollView scrollView;

    private List<String> mDatas;
    private static final String ARG_TITLE = "title";
    private String mTitle;

    public static MainFragment getInstance(String title) {
        MainFragment fra = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        fra.setArguments(bundle);
        return fra;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTitle = bundle.getString(ARG_TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerview);
        scrollView = v.findViewById(R.id.scrollView);
        if(mTitle.equals("About"))
        {
            mRecyclerView.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
        else{
            mRecyclerView.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
            initData();
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
            mRecyclerView.setAdapter(new RecyclerAdapter(mRecyclerView.getContext(), mDatas));

        }

        return v;
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'H'; i++) {
            mDatas.add(mTitle + (char) i);
        }
    }


}
