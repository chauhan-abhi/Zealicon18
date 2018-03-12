package com.jss.abhi.zealicon.fragments;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.model.InnerData;
import com.jss.abhi.zealicon.recyclerview.adapters.OuterAdapter;
import com.jss.abhi.zealicon.utils.Jsonparser;
import com.ramotion.garlandview.TailLayoutManager;
import com.ramotion.garlandview.TailRecyclerView;
import com.ramotion.garlandview.TailSnapHelper;
import com.ramotion.garlandview.header.HeaderTransformer;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class ScheduleFragment extends Fragment {

    private final static int OUTER_COUNT = 4;
    private final static int INNER_COUNT = 4;
    List<List<InnerData>> outerData = new ArrayList<>();
    public TailRecyclerView rv;

    public static Fragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
      return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParsingEvents parsingEvents=new ParsingEvents();
        parsingEvents.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        rv = view.findViewById(R.id.daysRecyclerView);
        initRecyclerView(outerData);
        return view;
    }



    private void initRecyclerView(List<List<InnerData>> data) {
        ((TailLayoutManager)rv.getLayoutManager()).setPageTransformer(new HeaderTransformer());
        rv.setAdapter(new OuterAdapter(data));
        new TailSnapHelper().attachToRecyclerView(rv);
    }

    public class ParsingEvents extends AsyncTask<Void, Void, String> {
        HttpURLConnection conn;
        BufferedReader bufferedReader;
        String error;
        ParsingEvents() {

        }
        @Override
        protected String doInBackground(Void... params) {
            SharedPreferences s=getContext().getSharedPreferences("events",0);
            String day1array=s.getString(getString(R.string.day1events),getString(R.string.default_str));
            String day2array=s.getString(getString(R.string.day2events),getString(R.string.default_str));
            String day3array=s.getString(getString(R.string.day3events),getString(R.string.default_str));
            String day4array=s.getString(getString(R.string.day4events),getString(R.string.default_str));
            for(int i=0;i<OUTER_COUNT;i++){
                JSONArray jsonArray=new JSONArray();
                try {
                    switch (i) {
                        case 0:
                            jsonArray = new JSONArray(day1array);
                            break;
                        case 1:
                            jsonArray = new JSONArray(day2array);
                            break;
                        case 2:
                            jsonArray = new JSONArray(day3array);
                            break;
                        case 3:
                            jsonArray = new JSONArray(day4array);
                            break;
                        default:
                            jsonArray=new JSONArray();
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                List<InnerData> innerDataList = new ArrayList<>();
                for (int j=0;j<jsonArray.length();j++){
                    InnerData innerData=new InnerData();
                    try {
                        innerData=Jsonparser.toObject(jsonArray.getJSONObject(j).toString());
                        innerDataList.add(innerData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

               // innerDataList.add(new InnerData("CODERZ",12,"AB1",14));
               // innerDataList.add(new InnerData("Mechavoltz",13,"AB2",14));
               // innerDataList.add(new InnerData("Colaralo",15,"MPH",14));
                //innerData.add(new InnerData("Colaralo",16,"MPH",15));
                //innerData.add(new InnerData("Colaralo",12,"MPH",16));
                //innerData.add(new InnerData("Colaralo",13,"MPH",14));
                //innerData.add(new InnerData("Colaralo",15,"MPH",15));

                outerData.add(innerDataList);
            }
            return "";
        }
        @Override
        protected void onPostExecute(final String success) {
        }
    }
}
