package com.example.abhi.jsshndemo.recyclerview.viewholders;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.model.InnerData;

/**
 * Created by abhi on 1/2/18.
 */

public class InnerItem extends com.ramotion.garlandview.inner.InnerItem{

    public final View mInnerLayout;
    
    public final TextView mEventTitle;
    public final TextView mEventTime;
    public final TextView mEventLocation;
    public final TextView mEventDate;
    public final View mLine;
    
    private InnerData mInnerData;
    
    public InnerItem(View itemView) {
        super(itemView);
        mInnerLayout = ((ViewGroup)itemView).getChildAt(0);

        mEventTitle=itemView.findViewById(R.id.titleTextView);
        mEventTime=itemView.findViewById(R.id.timeTextView);
        mEventLocation=itemView.findViewById(R.id.locationTextView);
        mLine = itemView.findViewById(R.id.guideline);
        mEventDate = itemView.findViewById(R.id.dateTextView);

        mInnerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Inner","Item Clicked");
            }
        });

    }



    @Override
    protected View getInnerLayout() {
        return mInnerLayout;
    }

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

