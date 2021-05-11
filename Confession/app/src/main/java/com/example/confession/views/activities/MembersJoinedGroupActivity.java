package com.example.confession.views.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.confession.adapters.MemberJoinedGroupAdapter;
import com.example.confession.binders.group.GetMembersBinder;
import com.example.confession.binders.group.GetPendingMembersBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class MembersJoinedGroupActivity extends AppCompatActivity implements GetMembersBinder.View {
   private GetMembersBinder.Presenter presenterGetMembers;
   private MemberJoinedGroupAdapter membersAdapter;

   private SwipeRefreshLayout srl_refresh_members_joined;
   private ConfessionGroupInfo cgi;
   private ImageView iv_members_back;
   private TextView name_member_joined;
   private ImageView ava_member_joined;

   private RecyclerView rv_members_joined_item;
   private ItemTouchHelper ith;

   private Thread newThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void OnGetMembersSuccess(ArrayList<BasicUserInfo> members) {

    }

    @Override
    public void OnGetMembersFailure(String error) {

    }
}
