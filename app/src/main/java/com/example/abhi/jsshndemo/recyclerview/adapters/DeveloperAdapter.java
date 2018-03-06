package com.example.abhi.jsshndemo.recyclerview.adapters;


import android.content.Context;
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
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;

/**
 * Created by abhi on 15/2/18.
 */

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.DevViewHolder> {

  private Context context;
  private ArrayList<Developer> developerArrayList;

  public class DevViewHolder extends RecyclerView.ViewHolder{

    private CircleImageView profileImg;
    private TextView name,position;
    private FloatingActionButton gitFab;


    public DevViewHolder(View itemView) {
      super(itemView);
      context = itemView.getContext();
      profileImg = itemView.findViewById(R.id.devImageView);
      name = itemView.findViewById(R.id.nameTextView);
      position = itemView.findViewById(R.id.positionTextView);
      gitFab = itemView.findViewById(R.id.floatingActionButton);
    }
  }

  public DeveloperAdapter(ArrayList<Developer> developerArrayList) {
    this.developerArrayList = developerArrayList;
  }

 /* public void addDeveloper(List<Developer> developerList){
    this.developerArrayList.addAll(developerList);
    notifyDataSetChanged();
  }*/

  @Override

  public DevViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_developer_member,parent,false);
    return new DevViewHolder(view);
  }

  @Override public void onBindViewHolder(DeveloperAdapter.DevViewHolder holder, int position) {
    final Developer developer = developerArrayList.get(position);
    holder.name.setText(developer.getName());
    holder.position.setText(developer.getPosition());
    //holder.profileImg.setImageResource(R.drawable.avatar);
    if(!(developer.getImgurl().isEmpty() || developer.getImgurl() == null)) {
      Picasso.with(context).load(Uri.parse(developer.getImgurl())).placeholder(R.drawable.avatar).into(holder.profileImg);
    }
    else {
      holder.profileImg.setImageResource(R.drawable.avatar);
    }


    holder.gitFab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Log.v("onClick Fab",""+developer.getGitHub());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(developer.getGitHub()));
        context.startActivity(intent);
      }
    });

  }

  @Override public int getItemCount() {
    return developerArrayList.size();
  }
}
