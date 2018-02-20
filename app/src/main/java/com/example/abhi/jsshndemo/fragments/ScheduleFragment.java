package com.example.abhi.jsshndemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.model.InnerData;
import com.example.abhi.jsshndemo.recyclerview.adapters.OuterAdapter;
import com.ramotion.garlandview.TailLayoutManager;
import com.ramotion.garlandview.TailRecyclerView;
import com.ramotion.garlandview.TailSnapHelper;
import com.ramotion.garlandview.header.HeaderTransformer;

import java.util.ArrayList;
import java.util.List;


public class ScheduleFragment extends Fragment {

    private final static int OUTER_COUNT = 4;
    private final static int INNER_COUNT = 4;
    List<List<InnerData>> outerData = new ArrayList<>();
    public TailRecyclerView rv;

    private TextView mtitle;

    public static Fragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
      return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0;i<OUTER_COUNT;i++){
            List<InnerData> innerData = new ArrayList<>();
            innerData.add(new InnerData("CODERZ","12:00","AB1","March 13, 2018"));
            innerData.add(new InnerData("Mechavoltz","13:00","AB2","March 13, 2018"));
            innerData.add(new InnerData("Colaralo","15:00","MPH","March 13, 2018"));
            innerData.add(new InnerData("Colaralo","15:00","MPH","March 13, 2018"));
            innerData.add(new InnerData("Colaralo","15:00","MPH","March 13, 2018"));
            innerData.add(new InnerData("Colaralo","15:00","MPH","March 13, 2018"));
            innerData.add(new InnerData("Colaralo","15:00","MPH","March 13, 2018"));

            outerData.add(innerData);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        rv = view.findViewById(R.id.daysRecyclerView);
        mtitle= view.findViewById(R.id.textViewTitle);
        initRecyclerView(outerData);
        return view;
    }


    private void initRecyclerView(List<List<InnerData>> data) {
        ((TailLayoutManager)rv.getLayoutManager()).setPageTransformer(new HeaderTransformer());
        rv.setAdapter(new OuterAdapter(data,getActivity()));
        new TailSnapHelper().attachToRecyclerView(rv);
    }
}
