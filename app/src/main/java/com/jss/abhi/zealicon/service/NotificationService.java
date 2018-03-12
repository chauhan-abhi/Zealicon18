package com.jss.abhi.zealicon.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.jss.abhi.zealicon.R;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NotificationService extends IntentService {
    public NotificationService(String name) {
        super(name);
    }
    public NotificationService(){
        super("hi");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
// build notification
        Log.v("work","work");
        String eventname=intent.getStringExtra("eventname");
        String message=eventname+" is going to start. Make sure to be there.";
// the addAction re-use the same intent to keep the example short
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,"1")
                        .setSmallIcon(R.drawable.corpbank)
                        .setContentTitle("Hurry up!!!!")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                        .setContentText(message);
        int NOTIFICATION_ID = intent.getIntExtra("keynotify",1);
        Log.v("keynotify2",NOTIFICATION_ID+"");
        // SharedPreferences sf=getSharedPreferences("notify",0);

        //  Intent targetIntent = new Intent(this, MyFavoriteActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(contentIntent);
        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(NOTIFICATION_ID, builder.build());
        // sf.edit().putInt("key",NOTIFICATION_ID+1).commit();
    }

    public static class AlarmReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            int notification=intent.getIntExtra("keynotify",1);
            String eventname=intent.getStringExtra("eventname");
            Intent sendIntent=new Intent(context,NotificationService.class);
            sendIntent.putExtra("keynotify",notification);
            sendIntent.putExtra("eventname",eventname);

            context.startService(sendIntent);
        }
    }
}
