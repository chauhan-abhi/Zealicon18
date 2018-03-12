package com.jss.abhi.zealicon;

import android.app.Application;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by abhi on 21/2/18.
 */

public class App extends Application {
  private FirebaseAnalytics mFirebaseAnalytics;
  @Override public void onCreate() {
    super.onCreate();
    mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
  }
}
