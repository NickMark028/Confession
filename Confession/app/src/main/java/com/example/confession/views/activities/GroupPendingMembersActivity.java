package com.example.confession.views.activities;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.confession.adapters.GroupPendingMembersAdapter;
import com.example.confession.binders.group.GetMembersBinder;
import com.example.confession.models.data.BasicUserInfo;

import java.util.ArrayList;

public class GroupPendingMembersActivity extends AppCompatActivity{

    private ArrayList<BasicUserInfo> user_info;
    private GroupPendingMembersAdapter pendingAdapter;

    private SwipeRefreshLayout srl_refresh_pending;
    private BasicUserInfo bus;
    private ImageView ava_user_pending,accept_pending_member,reject_pending_members;
    private RecyclerView re_pending_item;
    private ItemTouchHelper ith;

    private Thread newThread;



}
