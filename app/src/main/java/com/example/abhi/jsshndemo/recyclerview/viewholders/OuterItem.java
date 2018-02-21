package com.example.abhi.jsshndemo.recyclerview.viewholders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.model.InnerData;
import com.example.abhi.jsshndemo.recyclerview.adapters.InnerAdapter;
import com.ramotion.garlandview.header.HeaderDecorator;
import com.ramotion.garlandview.header.HeaderItem;
import com.ramotion.garlandview.inner.InnerLayoutManager;
import com.ramotion.garlandview.inner.InnerRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhi on 1/2/18.
 */

public class OuterItem extends HeaderItem {

    private final static float AVATAR_RATIO_START = 1f;
    private final static float AVATAR_RATIO_MAX = 0.25f;
    private final static float AVATAR_RATIO_DIFF = AVATAR_RATIO_START - AVATAR_RATIO_MAX;

    private final static float ANSWER_RATIO_START = 0.75f;
    private final static float ANSWER_RATIO_MAX = 0.35f;
    private final static float ANSWER_RATIO_DIFF = ANSWER_RATIO_START - ANSWER_RATIO_MAX;

    private final static float MIDDLE_RATIO_START = 0.7f;
    private final static float MIDDLE_RATIO_MAX = 0.1f;
    private final static float MIDDLE_RATIO_DIFF = MIDDLE_RATIO_START- MIDDLE_RATIO_MAX;

    private final static float FOOTER_RATIO_START = 1.1f;
    private final static float FOOTER_RATIO_MAX = 0.35f;
    private final static float FOOTER_RATIO_DIFF = FOOTER_RATIO_START - FOOTER_RATIO_MAX;

    private final View mHeader;
    private final View mHeaderAlpha;

    private InnerRecyclerView mRecyclerView;

    //private final ImageView mAvatar;
    //private final TextView mHeaderCaption1;
    //private final TextView mHeaderCaption2;

    //private final TextView mName;
    // private final TextView mInfo;

    //private final View mMiddle;
    //private final View mMiddleAnswer;
    //private final View mFooter;

    private final List<View> mMiddleCollapsible = new ArrayList<>(2);

    private final int m10dp;
    private final int m120dp;
    private final int mTitleSize1;
    private final int mTitleSize2;
    private  TextView mHeaderCaption1;

    private boolean mIsScrolling;

    public OuterItem(View itemView, RecyclerView.RecycledViewPool pool){
        super(itemView);

        //Init Header
        m10dp = itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp10);
        m120dp = itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp120);
        mTitleSize1 = itemView.getContext().getResources().getDimensionPixelSize(R.dimen.header_title1_text_size);
        mTitleSize2 = itemView.getContext().getResources().getDimensionPixelSize(R.dimen.header_title2_text_size);

        mHeader = itemView.findViewById(R.id.header);
        mHeaderAlpha = itemView.findViewById(R.id.header_alpha);
        mIsScrolling=false;


        mHeaderCaption1=itemView.findViewById(R.id.headerTextView);

        //Init RecyclerView
        mRecyclerView = itemView.findViewById(R.id.recycler_view);
        mRecyclerView.setRecycledViewPool(pool);
        mRecyclerView.setAdapter(new InnerAdapter());

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                mIsScrolling = newState !=RecyclerView.SCROLL_STATE_IDLE;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                onItemScrolled(recyclerView,dx,dy);
            }
        });

        mRecyclerView.addItemDecoration(new HeaderDecorator(
                itemView.getContext().getResources().getDimensionPixelSize(R.dimen.inner_item_height),
                itemView.getContext().getResources().getDimensionPixelSize(R.dimen.inner_item_offset)
        ));

    }


    @Override
    public boolean isScrolling() {
        return mIsScrolling;
    }

    @Override
    public InnerRecyclerView getViewGroup(){ return mRecyclerView; }

    @Override
    public View getHeader() {
        return mHeader;
    }

    @Override
    public View getHeaderAlphaView() {
        return mHeaderAlpha;
    }

    public void setContent(@NonNull List<InnerData> innerDataList){

        Context context=itemView.getContext();
        String title = "DAY 1";
        final List<InnerData> tail= innerDataList;
        mRecyclerView.setLayoutManager(new InnerLayoutManager());
        ((InnerAdapter)mRecyclerView.getAdapter()).addData(tail);
        mHeaderCaption1.setText(title);

    }

    public void clearContent(){
        ((InnerAdapter)mRecyclerView.getAdapter()).clearData();
    }

    private float computeRatio(RecyclerView recyclerView) {
        final View child0 = recyclerView.getChildAt(0);
        final int pos = recyclerView.getChildAdapterPosition(child0);
        if (pos != 0) {
            return 0;
        } else {

            final int height = child0.getHeight();
            final float y = Math.max(0, child0.getY());
            return y / height;
        }
    }

    private void onItemScrolled(RecyclerView recyclerView, int dx, int dy){

        final float ratio = computeRatio(recyclerView);
        final float footerRatio = Math.max(0, Math.min(FOOTER_RATIO_START, ratio) - FOOTER_RATIO_DIFF) / FOOTER_RATIO_MAX;
        final float answerRatio = Math.max(0, Math.min(ANSWER_RATIO_START, ratio) - ANSWER_RATIO_DIFF) / ANSWER_RATIO_MAX;
        final float middleRatio = Math.max(0, Math.min(MIDDLE_RATIO_START, ratio) - MIDDLE_RATIO_DIFF) / MIDDLE_RATIO_MAX;


    }

}
