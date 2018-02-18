package com.example.abhi.jsshndemo.recyclerview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.model.Developer;
import java.util.ArrayList;

/**
 * Created by abhi on 19/2/18.
 */

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder> {

  private ArrayList<Developer> mSponsorList;

  public class SponsorViewHolder extends RecyclerView.ViewHolder {

    private ImageView profileImg;
    private TextView name,position;

    public SponsorViewHolder(View itemView) {
      super(itemView);
      name = itemView.findViewById(R.id.sponsor_name);
      position = itemView.findViewById(R.id.sponsor_category);
      profileImg = itemView.findViewById(R.id.sponsor_image);
    }
  }

  @Override public SponsorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sponsor,parent,false);
    return new SponsorViewHolder(view);
  }

  @Override public void onBindViewHolder(SponsorViewHolder holder, int position) {
    final Developer sponsor = mSponsorList.get(position);
    holder.name.setText(sponsor.getName());
    holder.position.setText(sponsor.getPosition());
    holder.profileImg.setImageResource(R.mipmap.ic_launcher_round);
  }

  @Override public int getItemCount() {
    return mSponsorList.size();
  }

  public SponsorAdapter(ArrayList<Developer> mTeamList) {
    this.mSponsorList = mTeamList;
  }






}
