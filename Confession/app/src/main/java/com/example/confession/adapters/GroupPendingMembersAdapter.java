package com.example.confession.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confession.R;

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
        View view = LayoutInflater.from(context).inflate(R.layout.layout_pending_members, parent, false);
        return new GroupPendingMembersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.InitDate(position);

    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public int getItemCount() {
        return groupPending.size() ;
    }


    public class ViewHoler extends RecyclerView.ViewHolder {
        private LinearLayout ll_group_pending_members;
        private ImageView ava_user_pending, accept_pending_member, reject_pending_members;
        private TextView pending_member_name;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            InitView(itemView);
            InitListener();
        }

        public void InitListener(View view) {

        }

        public void InitView(View itemView) {
        }

        public void InitDate(int position) {

        }
    }

    public class ViewHolder extends ViewHoler {
        public ViewHolder(View view) {
            super();
        }
    }
}
