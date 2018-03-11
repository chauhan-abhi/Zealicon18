package com.example.abhi.zealicon.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abhi.zealicon.R;
import net.grandcentrix.tray.AppPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SplashActivity extends AppCompatActivity {

  private static int SPLASH_TIME_OUT = 3000;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    AppPreferences appPreferences = new AppPreferences(getApplicationContext());
    if(appPreferences.getInt("firsttime",0)==0){
      requestjson();
    }
    else {
      Intent i = new Intent(getApplicationContext(),MainActivity.class);
      startActivity(i);
    }
  }

  private void requestjson() {
    final String backofficeUrl = "http://backoffice.zealicon.in/events";
    final RequestQueue requestQueue = Volley.newRequestQueue(this);
    final StringRequest eventRequest = new StringRequest(Request.Method.GET, backofficeUrl,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {

                Log.v("MyApp", response);
                if(!response.toString().equals("[]") || !response.equals("")) {
                  SharedPreferences s = getSharedPreferences("events", 0);
                  s.edit().putString("allevents", response).apply();
                  intializeSchedule();
                  SharedPreferences sf = getSharedPreferences("firsttime", 0);
                  sf.edit().putInt("first", 1).apply();
                }
                Intent in =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
                finish();

              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                //You can handle error here if you want
                Log.v("MyApp",error.toString());
                //Intent in=new Intent(getApplicationContext(),Retry.class);
                //startActivity(in);
              }
            });

    requestQueue.add(eventRequest);
    int socketTimeout = 2000;//20 seconds - change to what you want
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    eventRequest.setRetryPolicy(policy);
    //final RequestQueue requestQueue = Volley.newRequestQueue(this);
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        /*Intent i = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(i);
        finish();*/
      }
    },SPLASH_TIME_OUT);
  }

  void intializeSchedule(){
    SharedPreferences s=getSharedPreferences("events",0);
    String events=s.getString("allevents","[]");
    JSONArray eventsArray,day1Array,day2Array,day3Array,day4Array;
    day1Array=new JSONArray();
    day2Array=new JSONArray();
    day3Array=new JSONArray();
    day4Array=new JSONArray();
    try {
      eventsArray = new JSONArray(events);
      for (int i = 0; i < eventsArray.length(); i++) {
        JSONObject eventObject = eventsArray.getJSONObject(i);
        String timing = eventObject.getString("timing");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null; // You will need try/catch around this
        try {
          date = formatter.parse(timing);
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(date);
          int dateInt = calendar.get(Calendar.DATE);
          switch (dateInt) {
            case 14:
              day1Array.put(eventObject);
              break;
            case 15:
              day2Array.put(eventObject);
              break;
            case 16:
              day3Array.put(eventObject);
              break;
            case 17:
              day4Array.put(eventObject);
              break;
            default:

          }

        } catch (ParseException e) {
          e.printStackTrace();
        }
      }
      s.edit().putString("allevents", "[]").apply();
      s.edit().putString("day1events", day1Array.toString()).apply();
      s.edit().putString("day2events", day2Array.toString()).apply();
      s.edit().putString("day3events", day3Array.toString()).apply();
      s.edit().putString("day4events", day4Array.toString()).apply();
      Log.v("MyApp", "Schedule Distribution Done");
    } catch(JSONException e){
        e.printStackTrace();
      }


  }
}
