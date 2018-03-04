package com.example.abhi.jsshndemo.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.model.InnerData;

public class EventDetailsActivity extends AppCompatActivity {

  private TextView eventName;
  private TextView eventDesc;
  private TextView firstPrize;
  private TextView secPrize;
  private TextView contact;
  private ImageButton callButton;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_details);

    eventName = findViewById(R.id.text_event_details_name);
    eventDesc = findViewById(R.id.text_event_details_description);
    firstPrize = findViewById(R.id.text_event_details_firstprize);
    secPrize = findViewById(R.id.text_event_details_secondprize);
    contact = findViewById(R.id.text_event_details_contact);
    callButton = findViewById(R.id.callbutton_eventdetails);


    //*************Dummy Data***************//
    eventName.setText("Technovision");
    eventDesc.setText("Rules \n \n #1: dkjasdkjdkadkadkj \n #2 hfjdshgjhgfhghjfgdsjhf \n jkhfkjdhfkjshfkjdshkdsjhfkjsdhfjhds \n kjhdjfdjshf \n sds");
    firstPrize.setText("2000");
    secPrize.setText("1500");
    contact.setText("abhi-- 99####9248 \n hdgfh -- 99####9393");

    callButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "99990649248"));
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {
          // TODO: Consider calling
          //    ActivityCompat#requestPermissions
          // here to request the missing permissions, and then overriding
          //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
          //                                          int[] grantResults)
          // to handle the case where the user grants the permission. See the documentation
          // for ActivityCompat#requestPermissions for more details.
          return;
        }
        startActivity(intent);

      }
    });

    //Getting values from shared prefrences

  }
}
