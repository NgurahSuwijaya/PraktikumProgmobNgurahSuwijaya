package com.example.praktikumprogmobngurahsuwijaya;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList quest_id, quest, quest_role, quest_player, quest_status;

    ListAdapter(Activity activity, Context context, ArrayList quest_id, ArrayList quest, ArrayList quest_role, ArrayList quest_player, ArrayList quest_status){
        this.activity = activity;
        this.context = context;
        this.quest_id = quest_id;
        this.quest = quest;
        this.quest_role= quest_role;
        this.quest_player = quest_player;
        this.quest_status = quest_status;

    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final String sQuest = quest.get(position).toString();
        final String sQuest_id = quest_id.get(position).toString();
//        final String sQPlayer = quest_player.get(position).toString();
        final String sQStatus = quest_status.get(position).toString();
        final String sQRole = quest_role.get(position).toString();

        Log.d("nama", sQuest);

        holder.textViewQuest.setText(sQuest);
        holder.textViewRole.setText(sQRole);
        holder.textViewStatus.setText(sQStatus);

        holder.flowBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DoneActivity.class);
                intent.putExtra("id", sQuest_id);
//                intent.putExtra("player", sQPlayer);
                intent.putExtra("quest", sQuest);
                intent.putExtra("role", sQRole);
                intent.putExtra("status", sQStatus);
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quest_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewRole, textViewQuest, textViewStatus;
        ImageButton flowBTn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewQuest=itemView.findViewById(R.id.textViewQuest);
            textViewRole=itemView.findViewById(R.id.textViewRoleQuest);
            textViewStatus=itemView.findViewById(R.id.textViewStatus);
            flowBTn=itemView.findViewById(R.id.overflowBtn);
        }
    }
}
