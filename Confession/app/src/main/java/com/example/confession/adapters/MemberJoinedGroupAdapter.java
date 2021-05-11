package com.example.confession.adapters;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confession.R;
import com.example.confession.models.data.BasicUserInfo;

import java.util.ArrayList;

public class MemberJoinedGroupAdapter extends RecyclerView.Adapter<MemberJoinedGroupAdapter.ViewHolder> {
    Context context;
    ArrayList<BasicUserInfo> membersJoinedGroup;


    public MemberJoinedGroupAdapter(Context context, ArrayList<BasicUserInfo> membersJoinedGroup){
        this.context = context;
        this.membersJoinedGroup = membersJoinedGroup;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_member_joined_group, parent,false);
        return new MemberJoinedGroupAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.InitData(position);
    }



    @Override
    public int getItemCount() {
        return membersJoinedGroup.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_members_joined_group;
        private ImageView ava_member_joined;
        private TextView name_member_joined;

        public ViewHolder(View view) {
            super(view);
            InitView(view);
            InitListener();
        }
        public void InitView(View view) {
            ll_members_joined_group= view.findViewById(R.id.ll_members_joined_group);
            ava_member_joined= view.findViewById(R.id.ava_member_joined);
            name_member_joined=view.findViewById(R.id.name_member_joined);

        }
        public void InitData(int position) {
            BasicUserInfo member_info = membersJoinedGroup.get(position);
            ava_member_joined.setImageResource((Integer) member_info.avatar);
            name_member_joined.setText(member_info.name);
        }
        public void InitListener() {
            // nothing
        }



    }
}
