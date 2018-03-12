package com.jss.abhi.zealicon.recyclerview.viewholders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.activities.EventDetailsActivity;
import com.jss.abhi.zealicon.model.InnerData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by abhi on 1/2/18.
 */

public class InnerItem extends com.ramotion.garlandview.inner.InnerItem{

    private final View mInnerLayout;
    
    private final TextView mEventTitle;
    private final TextView mEventTime;
    private final TextView mEventLocation;
    private final TextView mEventDate;
    ImageView catIcon;
    private final TextView mEventCategory;


    private final Context c;

    private InnerData mInnerData;
    
    public InnerItem(final View itemView) {
        super(itemView);
        c = itemView.getContext();
        mInnerLayout = ((ViewGroup)itemView).getChildAt(0);

        mEventTitle = itemView.findViewById(R.id.titleTextView);
        mEventTime = itemView.findViewById(R.id.timeTextView);
        mEventLocation = itemView.findViewById(R.id.locationTextView);
        mEventCategory = itemView.findViewById(R.id.categoryTextView);
        mEventDate = itemView.findViewById(R.id.dateTextView);
        catIcon=itemView.findViewById(R.id.catIcon);
        mInnerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Inner","Item Clicked");
                Intent i = new Intent(c,EventDetailsActivity.class);
                i.putExtra("eventData",mInnerData);
                c.startActivity(i);

            }
        });

    }

    @Override
    protected View getInnerLayout() {
        return mInnerLayout;
    }

    //Subscribe method for event bus
    public InnerData getItemData() {
        return mInnerData;
    }

    public void setContent(InnerData data){
        mInnerData = data;
        mEventTitle.setText(data.getEvent_name());
        String outputStr="";
        if(data.getTimings()!=null) {
            String dateStr = data.getTimings();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date(); // You will need try/catch around this
            try {
                date = formatter.parse(dateStr);
                SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm a");
                outputStr=outputFormat.format(date);

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        mEventTime.setText(outputStr);
        mEventLocation.setText(data.event_location);
        mEventCategory.setText(data.getCategory());
        if(data.getCategory()!=null) {
            if (data.getCategory().equals("Coderz")) {
                catIcon.setImageResource(R.drawable.coderz);
            } else if (data.getCategory().equals("Mechavoltz")) {
                catIcon.setImageResource(R.drawable.mechavoltz);
            } else if (data.getCategory().equals("Robotiles")) {
                catIcon.setImageResource(R.drawable.robotiles);
            } else if (data.getCategory().equals("Coloralo")) {
                catIcon.setImageResource(R.drawable.coloralo);
            } else if (data.getCategory().equals("Playiton")) {
                catIcon.setImageResource(R.drawable.playiton);
            } else if (data.getCategory().equals("Z-wars")) {
                catIcon.setImageResource(R.drawable.zwars);
            } else {
                catIcon.setImageResource(R.drawable.zwars);
            }
        }
  //      mEventDate.setText(data.getEvent_date());
    }

    public void clearContent(){
        mInnerData=null;
    }

}

