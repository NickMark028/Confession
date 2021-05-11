package com.example.confession.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(@NonNull MemberJoinedGroupAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    }
}
