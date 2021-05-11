package com.example.confession.views.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.confession.R;
import com.example.confession.adapters.GroupPendingMembersAdapter;
import com.example.confession.binders.group.GetMembersBinder;
import com.example.confession.binders.group.GetPendingMembersBinder;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.group.GetPendingMembersPresenter;

import java.util.ArrayList;

public class GroupPendingMembersActivity extends AppCompatActivity implements GetPendingMembersBinder.View {

    private GetPendingMembersBinder.Presenter presenterGetPendingMember;

    private ArrayList<BasicUserInfo> users_info;
    private GroupPendingMembersAdapter pendingAdapter;

    private SwipeRefreshLayout srl_refresh_pending;
    private ConfessionGroupInfo cgi;
    private TextView pending_member_name;
    private ImageView ava_user_pending,accept_pending_member,reject_pending_members;
    private RecyclerView rv_pending_item;
    private ItemTouchHelper ith;

    private Thread newThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_pending_members);

        cgi = (ConfessionGroupInfo) getIntent().getSerializableExtra("cgi");

        InitPresenter();
        InitView();
        InitListener();

        LoadPendingMember();

    }

    public void LoadPendingMember() {
        newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                presenterGetPendingMember.HandleGetPendingMembers(cgi);
            }
        });
        newThread.start();
        srl_refresh_pending.setRefreshing(true);
    }


    public void InitPresenter() {
        this.presenterGetPendingMember = new GetPendingMembersPresenter(this);
    }
    public void InitView() {
        ava_user_pending = findViewById(R.id.ava_user_pending);
        accept_pending_member =findViewById(R.id.accept_pending_member);
        reject_pending_members = findViewById(R.id.reject_pending_members);
        pending_member_name = findViewById(R.id.pending_member_name);

        rv_pending_item.findViewById(R.id.rv_cmt_item);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());

        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);

        rv_pending_item.setLayoutManager(llm);
        users_info = new ArrayList<>();
        pendingAdapter = new GroupPendingMembersAdapter(GroupPendingMembersActivity.this, users_info);
        rv_pending_item.setAdapter(pendingAdapter);
    }

    public void InitListener() {
    }





    @Override
    public void OnGetPendingMembersSuccess(ArrayList<BasicUserInfo> members) {

    }

    @Override
    public void OnGetPendingMembersFailure(String error) {

    }
}
