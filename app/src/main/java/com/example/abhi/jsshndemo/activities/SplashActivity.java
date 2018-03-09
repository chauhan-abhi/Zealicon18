package com.example.abhi.jsshndemo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.abhi.jsshndemo.R;
import net.grandcentrix.tray.AppPreferences;

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
    final StringRequest stringRequestcoderz = new StringRequest(Request.Method.GET, backofficeUrl+"0",
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {

                Log.v("MyApp", response);
                SharedPreferences s=getSharedPreferences("events",0);
                s.edit().putString("allevents",response).apply();
                SharedPreferences sf=getSharedPreferences("firsttime",0);
                sf.edit().putInt("first", 1).apply();
                Intent in =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);


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

    //final RequestQueue requestQueue = Volley.newRequestQueue(this);
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        Intent i = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(i);
        finish();
      }
    },SPLASH_TIME_OUT);
  }
}
