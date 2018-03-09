package com.example.abhi.jsshndemo.recyclerview.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.abhi.jsshndemo.R;
import com.example.abhi.jsshndemo.model.Developer;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;

/**
 * Created by abhi on 19/2/18.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder>{

  private ArrayList<Developer> mTeamList;
  private Context context;

  public class TeamViewHolder extends RecyclerView.ViewHolder {

    private CircleImageView profileImg;
    private TextView name,position;
    private FloatingActionButton callFab;

    public TeamViewHolder(View itemView) {
      super(itemView);
      context = itemView.getContext();
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
    //holder.profileImg.setImageResource(R.drawable.avatar);
    if(!(team.getImgurl().isEmpty() || team.getImgurl() == null)){
      Picasso.with(context)
          .load(Uri.parse(team.getImgurl()))
          .placeholder(R.drawable.avatar)
          .into(holder.profileImg);
    }
    else {
      holder.profileImg.setImageResource(R.drawable.avatar);
    }

    holder.callFab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Log.v("onClick Call Fab",""+team.getGitHub());
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + team.getGitHub()));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {
          // TODO: Consider calling
          //    ActivityCompat#requestPermissions
          // here to request the missing permissions, and then overriding
          //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
          //                                          int[] grantResults)
          // to handle the case where the user grants the permission. See the documentation
          // for ActivityCompat#requestPermissions for more details.
          return;
        }
        context.startActivity(intent);
      }
    });
  }

  @Override public int getItemCount() {
    return mTeamList.size();
  }


}
