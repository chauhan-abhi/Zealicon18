package com.example.abhi.jsshndemo.recyclerview.adapters;


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
 * Created by abhi on 15/2/18.
 */

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.DevViewHolder> {

  private ArrayList<Developer> developerArrayList;

  public class DevViewHolder extends RecyclerView.ViewHolder{

    private ImageView profileImg;
    private TextView name,position;
    private FloatingActionButton gitFab;


    public DevViewHolder(View itemView) {
      super(itemView);
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
    //holder.profileImg.setImageResource(R.drawable.avatar_placeholder);
    holder.profileImg.setImageResource(R.drawable.avatar);
    holder.gitFab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Log.v("onClick Fab",""+developer.getGitHub());
      }
    });

  }

  @Override public int getItemCount() {
    return developerArrayList.size();
  }
}
