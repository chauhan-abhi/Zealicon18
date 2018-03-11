package com.example.abhi.zealicon.recyclerview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhi.zealicon.R;
import com.example.abhi.zealicon.model.InnerData;
import com.example.abhi.zealicon.recyclerview.viewholders.OuterItem;
import com.ramotion.garlandview.TailAdapter;

import java.util.List;

/**
 * Created by abhi on 1/2/18.
 */

public class OuterAdapter extends TailAdapter<OuterItem> {
    private final int POOL_SIZE = 16;

    private final List<List<InnerData>> mData;
    private final RecyclerView.RecycledViewPool mPool;

    public OuterAdapter(List<List<InnerData>> data) {
        this.mData = data;

        mPool = new RecyclerView.RecycledViewPool();
        mPool.setMaxRecycledViews(0, POOL_SIZE);
    }
    @Override
    public OuterItem onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new OuterItem(view, mPool);
    }

    @Override
    public void onBindViewHolder(OuterItem holder, int position) {
        holder.setContent(mData.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.event_outer_item;
    }

    @Override
    public void onViewRecycled(OuterItem holder) {
        holder.clearContent();
    }
}
