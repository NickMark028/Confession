package com.example.confession.views.activities;

import android.os.Bundle;
import android.view.View;
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
import com.example.confession.adapters.MemberJoinedGroupAdapter;
import com.example.confession.binders.group.GetMembersBinder;
import com.example.confession.binders.group.GetPendingMembersBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.group_done.GetMembersPresenter;

import java.util.ArrayList;

public class MembersJoinedGroupActivity extends AppCompatActivity implements GetMembersBinder.View {
   private GetMembersBinder.Presenter presenterGetMembers;
   private MemberJoinedGroupAdapter membersAdapter;
   private ArrayList<BasicUserInfo> members_info;
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
        setContentView(R.layout.activity_member_joined_group);

        cgi= (ConfessionGroupInfo) getIntent().getSerializableExtra("cgi");

        InitPresenter();
        InitView();
        InitListener();

        LoadMembersJoined();

    }

    public void LoadMembersJoined() {
    newThread = new Thread(new Runnable() {
        @Override
        public void run() {
            presenterGetMembers.HandleGetMembers(cgi);
        }
    });
    newThread.start();
    srl_refresh_members_joined.setRefreshing((true));
    }

    public void InitListener() {
        iv_members_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void InitView() {
        srl_refresh_members_joined = findViewById(R.id.srl_refresh_pending);
        ava_member_joined = findViewById(R.id.ava_member_joined);
        name_member_joined = findViewById(R.id.name_member_joined);

        rv_members_joined_item = findViewById(R.id.rv_members_joined_item);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());

        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);

        rv_members_joined_item.setLayoutManager(llm);
        members_info = new ArrayList<>();
        membersAdapter = new MemberJoinedGroupAdapter(MembersJoinedGroupActivity.this, members_info);
        rv_members_joined_item.setAdapter(membersAdapter);

        iv_members_back.setClickable(false);

    }

    public void InitPresenter() {
        this.presenterGetMembers= new GetMembersPresenter(this);
    }

    @Override
    public void OnGetMembersSuccess(ArrayList<BasicUserInfo> members) {

    }

    @Override
    public void OnGetMembersFailure(String error) {

    }
}
