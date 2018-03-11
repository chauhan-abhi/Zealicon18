package com.example.abhi.zealicon.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.abhi.zealicon.R;

public class ZealIDActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_zeal_id);
    findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        finish();
        //nulify prev entries
      }
    });

  }
  @Override
  protected void onPause() {
    super.onPause();
    finish();
  }
}
