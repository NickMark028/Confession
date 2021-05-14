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
import com.example.confession.adapters.user.MemberJoinedGroupAdapter;
import com.example.confession.binders.group.GetMembersBinder;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.group.GetMembersPresenter;

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

		cgi = (ConfessionGroupInfo) getIntent().getSerializableExtra("cgi");

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
				finish();
			}
		});

		srl_refresh_members_joined.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				LoadMembersJoined();
			}
		});
	}

	public void InitView() {
		srl_refresh_members_joined = findViewById(R.id.srl_refresh_pending);
		ava_member_joined = findViewById(R.id.ava_member_joined);
		name_member_joined = findViewById(R.id.name_member_joined);
		iv_members_back = findViewById(R.id.iv_members_back);
		srl_refresh_members_joined = findViewById(R.id.srl_refresh_members_joined);

		rv_members_joined_item = findViewById(R.id.rv_members_joined_item);

		LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());

//        llm.setReverseLayout(true);
//        llm.setStackFromEnd(true);

		rv_members_joined_item.setLayoutManager(llm);

		members_info = new ArrayList<>();
		membersAdapter = new MemberJoinedGroupAdapter(MembersJoinedGroupActivity.this, members_info);
		rv_members_joined_item.setAdapter(membersAdapter);

	}

	public void InitPresenter() {
		this.presenterGetMembers = new GetMembersPresenter(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (newThread != null && newThread.isAlive()) {
			newThread.interrupt();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	public void OnGetMembersSuccess(ArrayList<BasicUserInfo> members) {
		members_info.clear();
		members_info.addAll(members);

		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(MembersJoinedGroupActivity.this, "Get Successfully", Toast.LENGTH_SHORT).show();
//                LoadMembersJoined();
				srl_refresh_members_joined.setRefreshing(false);

				membersAdapter.notifyDataSetChanged();
				rv_members_joined_item.invalidateItemDecorations();
				rv_members_joined_item.refreshDrawableState();
			}
		});
	}

	@Override
	public void OnGetMembersFailure(String error) {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(MembersJoinedGroupActivity.this, "Get Failed", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
