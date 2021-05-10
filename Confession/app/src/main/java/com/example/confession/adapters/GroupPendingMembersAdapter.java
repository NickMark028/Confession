package com.example.confession.adapters;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupPendingMembersAdapter extends RecyclerView.Adapter<GroupPendingMembersAdapter.ViewHoler> {
    Context context;
    ArrayList<GroupPendingMembersAdapter> groupPending;

    public GroupPendingMembersAdapter(Context context, ArrayList<GroupPendingMembersAdapter> groupPending){
        this.context = context;
        this.groupPending = groupPending;
    }
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
            InitView(itemView);
            InitListener();
        }

        public void InitListener() {
        }

        public void InitView(View itemView) {
        }
    }
}
