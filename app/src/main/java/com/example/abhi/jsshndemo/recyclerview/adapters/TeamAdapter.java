package com.example.abhi.jsshndemo.recyclerview.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder>{

  private ArrayList<Developer> mTeamList;

  public class TeamViewHolder extends RecyclerView.ViewHolder {

    private ImageView profileImg;
    private TextView name,position;
    private FloatingActionButton callFab;

    public TeamViewHolder(View itemView) {
      super(itemView);
      profileImg = itemView.findViewById(R.id.teamImageView);
      name = itemView.findViewById(R.id.memberName);
      position = itemView.findViewById(R.id.memberPosition);
      callFab = itemView.findViewById(R.id.call);
    }
  }

  public TeamAdapter(ArrayList<Developer> mTeamList) {
    this.mTeamList = mTeamList;
  }

  @Override public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_team_member,parent,false);
    return new TeamViewHolder(view);
  }

  @Override public void onBindViewHolder(TeamViewHolder holder, int position) {
    final Developer team = mTeamList.get(position);
    holder.name.setText(team.getName());
    holder.position.setText(team.getPosition());
    holder.profileImg.setImageResource(R.drawable.avatar);
    holder.callFab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Log.v("onClick Call Fab",""+team.getGitHub());
        //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + team.getGitHub()));

      }
    });
  }

  @Override public int getItemCount() {
    return mTeamList.size();
  }


}
