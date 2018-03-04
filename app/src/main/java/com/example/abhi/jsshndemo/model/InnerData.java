package com.example.abhi.jsshndemo.model;


/**
 * Created by abhi on 1/2/18.
 */

public class InnerData {

    public String event_name;
    public String event_time;
    public String event_location;
    public String event_date;

    public InnerData(String event_name, String event_time, String event_location, String event_date) {
        this.event_name = event_name;
        this.event_time = event_time;
        this.event_location = event_location;
        this.event_date = event_date;
    }
}