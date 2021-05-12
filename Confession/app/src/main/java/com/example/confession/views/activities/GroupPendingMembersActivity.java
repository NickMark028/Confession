package com.example.confession.views.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.confession.R;
import com.example.confession.adapters.user.GroupPendingMembersAdapter;
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
    private ImageView iv_pending_back;
    private TextView pending_member_name;
    private ImageView ava_user_pending,accept_pending_member,reject_pending_members;
    private RecyclerView rv_pending_item;
    private  TextView txt_accept_all;
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

        accept_pending_member =findViewById(R.id.accept_pending_member);
        reject_pending_members = findViewById(R.id.reject_pending_members);
        pending_member_name = findViewById(R.id.pending_member_name);
        srl_refresh_pending = findViewById(R.id.srl_refresh_pending);
        rv_pending_item = findViewById(R.id.rv_pending_item);

        iv_pending_back = findViewById(R.id.iv_pending_back);
        txt_accept_all = findViewById(R.id.txt_accept_all);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());

        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);

        rv_pending_item.setLayoutManager(llm);

        users_info = new ArrayList<>();
        pendingAdapter = new GroupPendingMembersAdapter(GroupPendingMembersActivity.this, users_info);
        rv_pending_item.setAdapter(pendingAdapter);


        txt_accept_all.setEnabled(false);
//        accept_pending_member.setEnabled(false);
//        reject_pending_members.setEnabled(false);
    }

    public void InitListener() {
        iv_pending_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish();

            }
        });

        txt_accept_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        srl_refresh_pending.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadPendingMember();
            }
        });


    }


    @Override
    public void OnGetPendingMembersSuccess(ArrayList<BasicUserInfo> users_pending) {
        users_info.clear();
        users_info.addAll(users_pending);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                srl_refresh_pending.setRefreshing(false);
                pendingAdapter.notifyDataSetChanged();
                rv_pending_item.invalidateItemDecorations();
                rv_pending_item.refreshDrawableState();
            }
        });

    }

    @Override
    public void OnGetPendingMembersSuccess(BasicUserInfo members) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(GroupPendingMembersActivity.this, "Sent Successfully", Toast.LENGTH_SHORT).show();
                //LoadPendingMember();
            }
        });
    }


    @Override
    public void OnGetPendingMembersFailure(String error) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(GroupPendingMembersActivity.this, "Sent Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
