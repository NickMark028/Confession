package com.example.confession.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroupPendingMembers extends RecyclerView.Adapter<GroupPendingMembers.ViewHoler> {
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
        }
    }
}
