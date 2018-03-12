package com.jss.abhi.zealicon.model;


import java.io.Serializable;

/**
 * Created by abhi on 1/2/18.
 */

public class InnerData implements Serializable{

    public String event_name;
    public int event_time;
    public String event_location;
    public String timings;
    public int event_date;
    public String contact_name1;
    public String contact_name2;
    public String contact_num1;
    public String contact_num2;
    public String prize1;
    public String prize2;
    public String event_description;
    public String long_des;
    public String category;
    public String rules;

    public InnerData(){

    }
    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public int getEvent_time() {
        return event_time;
    }

    public void setEvent_time(int event_time) {
        this.event_time = event_time;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public int getEvent_date() {
        return event_date;
    }

    public void setEvent_date(int event_date) {
        this.event_date = event_date;
    }

    public String getContact_name1() {
        return contact_name1;
    }

    public void setContact_name1(String contact_name1) {
        this.contact_name1 = contact_name1;
    }

    public String getContact_name2() {
        return contact_name2;
    }

    public void setContact_name2(String contact_name2) {
        this.contact_name2 = contact_name2;
    }

    public String getContact_num1() {
        return contact_num1;
    }

    public void setContact_num1(String contact_num1) {
        this.contact_num1 = contact_num1;
    }

    public String getContact_num2() {
        return contact_num2;
    }

    public void setContact_num2(String contact_num2) {
        this.contact_num2 = contact_num2;
    }

    public String getPrize1() {
        return prize1;
    }

    public void setPrize1(String prize1) {
        this.prize1 = prize1;
    }

    public String getPrize2() {
        return prize2;
    }

    public void setPrize2(String prize2) {
        this.prize2 = prize2;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getLong_des() {
        return long_des;
    }

    public void setLong_des(String long_des) {
        this.long_des = long_des;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public InnerData(String event_name, int event_time, String event_location, int event_date) {
        this.event_name = event_name;
        this.event_time = event_time;
        this.event_location = event_location;
        this.event_date = event_date;
    }
}