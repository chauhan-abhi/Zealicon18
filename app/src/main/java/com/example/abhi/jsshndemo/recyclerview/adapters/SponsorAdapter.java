package com.example.abhi.jsshndemo.recyclerview.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.model.Developer;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by abhi on 19/2/18.
 */

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder> {

  private Context context;
  private ArrayList<Developer> mSponsorList;

  public class SponsorViewHolder extends RecyclerView.ViewHolder {

    private ImageView profileImg;
    private TextView name,position;

    public SponsorViewHolder(View itemView) {
      super(itemView);
      context = itemView.getContext();
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
    if(!(sponsor.getImgurl().isEmpty() || sponsor.getImgurl() == null)) {
      Picasso.with(context)
          .load(Uri.parse(sponsor.getImgurl()))
          .placeholder(R.mipmap.ic_launcher_round)
          .into(holder.profileImg);
    }
    else {
      holder.profileImg.setImageResource(R.drawable.corpbank);
    }

  }

  @Override public int getItemCount() {
    return mSponsorList.size();
  }

  public SponsorAdapter(ArrayList<Developer> mTeamList) {
    this.mSponsorList = mTeamList;
  }






}
