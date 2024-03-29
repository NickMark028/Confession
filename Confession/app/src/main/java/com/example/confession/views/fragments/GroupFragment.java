package com.example.confession.views.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.adapters.post.PostAdapter;
import com.example.confession.binders.group.GetPostsBinder;
import com.example.confession.binders.user.GetUserState;
import com.example.confession.binders.user.JoinGroupBinder;
import com.example.confession.listener.BottomSheetManageGroupListener;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.UserState;
import com.example.confession.presenters.group.GetPostsPresenter;
import com.example.confession.presenters.user.GetUserStatePresenter;
import com.example.confession.presenters.user.JoinGroupPresenter;
import com.example.confession.views.activities.GroupPendingMembersActivity;
import com.example.confession.views.activities.MembersJoinedGroupActivity;
import com.example.confession.views.bottomsheet.GroupAdminManageGroupBottomSheet;
import com.example.confession.views.bottomsheet.GroupUserManageBottomSheet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupFragment extends Fragment
		implements
		GetPostsBinder.View,
		GetUserState.View,
		JoinGroupBinder.View,
		BottomSheetManageGroupListener {

	private GetPostsBinder.Presenter presenter;
	private GetUserState.Presenter presenterUserState;
	private JoinGroupBinder.Presenter presenterJoinGroup;

	private ConfessionGroupInfo cgi;

	private String mTag;
	private int numberOfPosts;
	private UserState userState;

	private String user_role = "ROLE_ADMIN";
	private LinearLayout ll_noti_join_group, ll_group_member, ll_group_loading;
	private TextView txt_gr_name, txt_group_mem_count, txt_group_post_count;

	private RecyclerView rv_group_posts;
	private SwipeRefreshLayout srl_group_posts;
	private PostAdapter postAdapter;
	private ArrayList<GroupPostInfo> gr_posts;

	private ImageView iv_group_back_btn, iv_group_setting_btn;
	private AppCompatButton btn_join_group;

	private GroupAdminManageGroupBottomSheet bottomSheetAdmin;
	private GroupUserManageBottomSheet bottomSheetUser;

	private Thread newThread, loadPostThread;

	public static GroupFragment newInstance(ConfessionGroupInfo cgi) {

		GroupFragment fragment = new GroupFragment();
		Bundle args = new Bundle();
		args.putSerializable("group_info", cgi);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments() != null) {
			cgi = (ConfessionGroupInfo) getArguments().getSerializable("group_info");
		}
		mTag = this.getTag();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_group, container, false);

		InitPresenter();
		InitView(view);
		InitBottomSheet();
		InitListener();
		InitData();

		CheckUserInGroup();
//		LoadGroupPosts();
		return view;
	}

	private void CheckUserInGroup() {
		newThread = new Thread(new Runnable() {
			@Override
			public void run() {
				presenterUserState.HandleGetUserState(cgi);
			}
		});

		newThread.start();
	}

	public void InitPresenter() {
		presenter = new GetPostsPresenter(this);
		presenterUserState = new GetUserStatePresenter(this);
		presenterJoinGroup = new JoinGroupPresenter(this);
	}

	private void InitBottomSheet() {
		bottomSheetAdmin = new GroupAdminManageGroupBottomSheet(this);
		bottomSheetUser = new GroupUserManageBottomSheet(this);
	}

	public void InitView(View view) {

		txt_gr_name = view.findViewById(R.id.txt_gr_name);
		txt_group_mem_count = view.findViewById(R.id.txt_group_mem_count);
		txt_group_post_count = view.findViewById(R.id.txt_group_post_count);

		ll_noti_join_group = view.findViewById(R.id.ll_noti_join_group);
		ll_group_member = view.findViewById(R.id.ll_group_member);
		ll_group_loading = view.findViewById(R.id.ll_group_loading);

		iv_group_back_btn = view.findViewById(R.id.iv_group_back_btn);
		iv_group_setting_btn = view.findViewById(R.id.iv_group_setting_btn);
		btn_join_group = view.findViewById(R.id.btn_join_group);

		//Init RecyclerView and SwipeRefreshLayout
		srl_group_posts = view.findViewById(R.id.srl_group_posts);
		rv_group_posts = view.findViewById(R.id.rv_group_posts);
		LinearLayoutManager llm = new LinearLayoutManager(getContext());
//		llm.setReverseLayout(true);
//		llm.setStackFromEnd(true);
		rv_group_posts.setLayoutManager(llm);

		gr_posts = new ArrayList<>();
		postAdapter = new PostAdapter(getContext(), gr_posts);
		rv_group_posts.setAdapter(postAdapter);

		//Set Visibility
		btn_join_group.setVisibility(View.GONE);
		ll_noti_join_group.setVisibility(View.GONE);
		//rv_group_posts.setVisibility(View.GONE);
		iv_group_setting_btn.setVisibility(View.GONE);
		srl_group_posts.setVisibility(View.GONE);
	}

	public void InitData() {
		txt_gr_name.setText(cgi.name);
		txt_group_mem_count.setText(Integer.toString(cgi.member_count));
	}

	public void LoadGroupPosts() {
		loadPostThread = new Thread(new Runnable() {
			@Override
			public void run() {
				presenter.HandleGetPosts(cgi);
			}
		});

		loadPostThread.start();

		srl_group_posts.setRefreshing(true);
//		Toast.makeText(getContext(), "Loading...", Toast.LENGTH_LONG).show();
	}

	public void InitListener() {
		iv_group_back_btn.setOnClickListener(v -> getFragmentManager().popBackStack());

		srl_group_posts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				//Do sth
				LoadGroupPosts();
			}
		});

		btn_join_group.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("SetTextI18n")
			@Override
			public void onClick(View v) {
				btn_join_group.setEnabled(false);
				btn_join_group.setText("Loading...");
				newThread = new Thread(new Runnable() {
					@Override
					public void run() {
						presenterJoinGroup.HandleJoinGroup(cgi.id);
					}
				});
				newThread.start();
			}
		});

		iv_group_setting_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (userState == UserState.Admin) {
					assert getFragmentManager() != null;
					bottomSheetAdmin.show(getFragmentManager(), "admin_settings");
				}
				else if (userState == UserState.Following) {
					assert getFragmentManager() != null;
					bottomSheetUser.show(getFragmentManager(), "user_settings");
				}
			}
		});
	}

	private void HandleResultFromAdminManagerBottomSheet(int result) {
//		Toast.makeText(getContext(), "Check result - " + result, Toast.LENGTH_LONG).show();
		switch (result) {
			case 0: //Open pending member
				Intent pendingIntent = new Intent(getContext().getApplicationContext(), GroupPendingMembersActivity.class);
				pendingIntent.putExtra("cgi", cgi);
				startActivity(pendingIntent);
				break;

			case 1: // Open member
				Intent memberIntent = new Intent(getContext().getApplicationContext(), MembersJoinedGroupActivity.class);
				memberIntent.putExtra("cgi", cgi);
				startActivity(memberIntent);
				break;

			case 2: // Delete group
				bottomSheetAdmin.dismiss();
				break;

			default:
				break;
		}
	}

	private void HandleResultFromUserManagerBottomSheet(int result) {
		switch (result) {
			case 0: //Open members
				Intent memberIntent = new Intent(getContext().getApplicationContext(), MembersJoinedGroupActivity.class);
				memberIntent.putExtra("cgi", cgi);
				startActivity(memberIntent);
				break;

			case 1: // Leave group
				bottomSheetUser.dismiss();
				break;

			case -1:
				break;
		}
	}

	//Setup UI base on USER ROLE
	public void SetupAdminUI() {
		ll_group_loading.setVisibility(View.GONE);
		srl_group_posts.setVisibility(View.VISIBLE);
		iv_group_setting_btn.setVisibility(View.VISIBLE);
	}

	public void SetupFollowingUI() {
		ll_group_loading.setVisibility(View.GONE);
		srl_group_posts.setVisibility(View.VISIBLE);
		iv_group_setting_btn.setVisibility(View.VISIBLE);
	}

	public void SetupNonMemberUI() {
		ll_group_loading.setVisibility(View.GONE);
		btn_join_group.setVisibility(View.VISIBLE);
		ll_noti_join_group.setVisibility(View.VISIBLE);
	}

	public void SetupPendingUI() {
		ll_group_loading.setVisibility(View.GONE);

		btn_join_group.setText("Requesting");
		btn_join_group.setVisibility(View.VISIBLE);
		btn_join_group.setEnabled(false);

		ll_noti_join_group.setVisibility(View.VISIBLE);
	}

	/*
		OVERRIDE FUNCTION IMPLEMENTATION HERE...
	*/

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (newThread != null && newThread.isAlive()) {
			newThread.interrupt();
		}
		if (loadPostThread != null && loadPostThread.isAlive()) {
			loadPostThread.interrupt();
		}
	}

	@Override
	public void OnGetPostsSuccess(ArrayList<GroupPostInfo> posts) {

		if (getActivity() == null) return;

		gr_posts.clear();
		gr_posts.addAll(posts);

		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				srl_group_posts.setRefreshing(false);

				postAdapter.notifyDataSetChanged();
				rv_group_posts.invalidateItemDecorations();
				rv_group_posts.refreshDrawableState();

				txt_group_post_count.setText(Integer.toString(posts.size()));
			}
		});
	}

	@Override
	public void OnGetPostsFailure(String error) {
		if (getActivity() == null) return;

		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				srl_group_posts.setRefreshing(false);
				Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public void OnGetUserStateSuccess(UserState user_state) {
		if (getActivity() == null) return;

		userState = user_state;

		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (user_state.equals(UserState.Admin)) {
					Log.e("Port: ", "Admin if");
					SetupAdminUI();
					LoadGroupPosts();
				}
				else if (user_state == UserState.Following) {
					SetupFollowingUI();
					LoadGroupPosts();
				}
				else if (user_state == UserState.NonMember) {
					SetupNonMemberUI();
				}
				else if (user_state == UserState.Pending) {
					SetupPendingUI();
				}
				else {
					//do nothing
				}
			}
		});
	}

	@Override
	public void OnGetUserStateFailure(String error) {
		if (getActivity() == null) return;

		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void OnJoinGroupSuccess() {
		if (getActivity() == null) return;

		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				btn_join_group.setText("Requesting");

				//btn_join_group.setVisibility(View.GONE);
//				ll_noti_join_group.setVisibility(View.GONE);
				btn_join_group.setEnabled(false);
			}
		});
	}

	@Override
	public void OnJoinGroupFailure(String error) {
		if (getActivity() == null) return;

		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				btn_join_group.setEnabled(true);
				btn_join_group.setText("Join group");
				Toast.makeText(getContext(), "Failed to join group", Toast.LENGTH_SHORT).show();
			}
		});
	}


	@Override
	public void onButtonUserClicked(int result) {
		HandleResultFromUserManagerBottomSheet(result);
	}

	@Override
	public void onButtonAdminClicked(int result) {
		HandleResultFromAdminManagerBottomSheet(result);
	}
}
