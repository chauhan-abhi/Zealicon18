package com.example.abhi.jsshndemo.recyclerview.viewholders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.activities.EventDetailsActivity;
import com.example.abhi.jsshndemo.model.InnerData;

/**
 * Created by abhi on 1/2/18.
 */

public class InnerItem extends com.ramotion.garlandview.inner.InnerItem{

    private final View mInnerLayout;
    
    private final TextView mEventTitle;
    private final TextView mEventTime;
    private final TextView mEventLocation;
    private final TextView mEventDate;

    private final Context c;

    private InnerData mInnerData;
    
    public InnerItem(final View itemView) {
        super(itemView);
        c = itemView.getContext();
        mInnerLayout = ((ViewGroup)itemView).getChildAt(0);

        mEventTitle = itemView.findViewById(R.id.titleTextView);
        mEventTime = itemView.findViewById(R.id.timeTextView);
        mEventLocation = itemView.findViewById(R.id.locationTextView);
        mEventDate = itemView.findViewById(R.id.dateTextView);

        mInnerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Inner","Item Clicked");
                Intent i = new Intent(c,EventDetailsActivity.class);
                //i.putExtra("Event",mInnerData);
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
        mEventTitle.setText(data.event_name);
        mEventTime.setText(data.event_time);
        mEventLocation.setText(data.event_location);
        mEventDate.setText(data.event_date);
    }

    public void clearContent(){
        mInnerData=null;
    }

}

