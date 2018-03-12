package com.jss.abhi.zealicon.activities;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.model.InnerData;
import com.jss.abhi.zealicon.service.NotificationService;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView eventName;
    private TextView eventDesc, eventLongDesc, eventTimings;
    private TextView firstPrize;
    private TextView secPrize;
    private TextView contact, rulesView, descriptionView;
    private ImageButton callButton;
    private ImageView catIconView;
    InnerData innerData;
    boolean detail=true;
    private SwitchCompat interestedSwitch, goingSwitch;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        eventName = findViewById(R.id.text_event_details_name);
        eventDesc = findViewById(R.id.text_event_details_description);
        eventLongDesc = findViewById(R.id.text_event_details_longdes);
        firstPrize = findViewById(R.id.text_event_details_firstprize);
        secPrize = findViewById(R.id.text_event_details_secondprize);
        contact = findViewById(R.id.text_event_details_contact);
        eventTimings = findViewById(R.id.text_event_details_timings);
        callButton = findViewById(R.id.callbutton_eventdetails);
        catIconView = findViewById(R.id.catIconDetail);
        rulesView = findViewById(R.id.text_event_details_rules);
        interestedSwitch = findViewById(R.id.switchcompat_interested);
        goingSwitch = findViewById(R.id.switchcompat_going);

        if (getIntent() != null) {
            innerData = (InnerData) getIntent().getSerializableExtra("eventData");

            if (innerData.getCategory() != null) {
                if (innerData.getCategory().equals("Coderz"))
                    catIconView.setImageResource(R.drawable.coderz);

                else if (innerData.getCategory().equals("Mechavoltz"))
                    catIconView.setImageResource(R.drawable.mechavoltz);

                else if (innerData.getCategory().equals("Coloralo"))
                    catIconView.setImageResource(R.drawable.coloralo);

                else if (innerData.getCategory().equals("Robotiles"))
                    catIconView.setImageResource(R.drawable.robotiles);

                else if (innerData.getCategory().equals("Z-wars"))
                    catIconView.setImageResource(R.drawable.zwars);

                else if (innerData.getCategory().equals("Play it On"))
                    catIconView.setImageResource(R.drawable.playiton);

            }

            eventName.setText(innerData.getEvent_name());
            eventDesc.setText(stripHtml(innerData.getEvent_description()));
            firstPrize.setText(innerData.getPrize1());
            secPrize.setText(innerData.getPrize2());
            contact.setText(innerData.getContact_name1() + " : " + innerData.getContact_num1() + "\n"
                    + innerData.getContact_name2() + " : " + innerData.getContact_num2());
            rulesView.setText(stripHtml(innerData.getRules()));
            eventLongDesc.setText(stripHtml(innerData.getLong_des()));

            String outputStr = "";
            if (innerData.getTimings() != null) {
                String dateStr = innerData.getTimings();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = new Date(); // You will need try/catch around this
                try {
                    date = formatter.parse(dateStr);
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM hh:mm a");
                    outputStr = outputFormat.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            eventTimings.setText(outputStr);
            int notifyId = getSharedPreferences("notify", 0).getInt(innerData.getEvent_name(), 0);
            if (notifyId == 1)
                goingSwitch.setChecked(true);
            else if (notifyId == 2)
                interestedSwitch.setChecked(true);
            else {
                goingSwitch.setChecked(false);
                interestedSwitch.setChecked(false);
            }

        }
        //*************Dummy Data***************/
        eventLongDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detail=!detail;
                if(detail)
                    eventLongDesc.setMaxLines(Integer.MAX_VALUE);
                else
                    eventLongDesc.setMaxLines(8);
            }
        });
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    ActivityCompat.requestPermissions(EventDetailsActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            0);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + innerData.getContact_num1()));
                startActivity(intent);

            }
        });

        goingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    notifyme();
                    Toast.makeText(getBaseContext(), "We will notify you..", Toast.LENGTH_SHORT).show();
                } else {

                    canclenotifyme();
                }
            }
        });
        interestedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    getSharedPreferences("notify", 0).edit()
                            .putInt(innerData.getEvent_name(), 2).apply();
                    Bundle bundle = new Bundle();
                    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, innerData.getEvent_name());
                    bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, innerData.getCategory());
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                } else {
                    getSharedPreferences("notify", 0).edit()
                            .putInt(innerData.getEvent_name(), 0).apply();
                }
            }
        });

    }

    private void canclenotifyme() {
        Intent intent = new Intent(this, NotificationService.AlarmReceiver.class);
        int id = getSharedPreferences("notify", 0).getInt(innerData.getEvent_name(), 1);
        PendingIntent sender = PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(sender);
        sender.cancel();
        getSharedPreferences("notify", 0).edit()
                .putInt(innerData.getEvent_name(), 0)
                .apply();
    }
    public String stripHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(html).toString();
        }
    }

    private void notifyme() {
        String toParse = innerData.getTimings(); // Results in "2-5-2012 20:43"
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // I assume d-M, you may refer to M-d for month-day instead.
        Date date = new Date(); // You will need try/catch around this
        try {
            date = formatter.parse(toParse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SharedPreferences sf = getSharedPreferences("notify", 0);
        int notificationid = sf.getInt("key", 1);
        sf.edit().putInt(innerData.getEvent_name(), 1).apply();
        long millis = date.getTime();
        Intent intent = new Intent(getApplicationContext(), NotificationService.AlarmReceiver.class);
        intent.putExtra("keynotify", notificationid);
        intent.putExtra("eventname", innerData.getEvent_name());
        Log.v("keynotify1", notificationid + "");

        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), notificationid, intent, PendingIntent.FLAG_ONE_SHOT);
        sf.edit().putInt("key", notificationid + 1).apply();
        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, millis-3600000, pi);
        AlarmManager am2 = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        am2.set(AlarmManager.RTC_WAKEUP, millis-900000, pi);
    }



    private void settextstyle(TextView tv1) {
        Typeface custom_font = Typeface.createFromAsset(getApplicationContext().getAssets(), "font/huggable.ttf");
        tv1.setTypeface(custom_font);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + innerData.getContact_num1()));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

}
