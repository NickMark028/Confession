package com.example.confession.views.fragments;

import android.annotation.SuppressLint;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.adapters.PostAdapter;
import com.example.confession.binders.group.GetPostsBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.presenters.group.GetPostsPresenter;
import com.example.confession.views.bottomsheet.GroupUserManageBottomSheet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupFragment extends Fragment implements GetPostsBinder.View {

	private GetPostsBinder.Presenter presenter;
	private ConfessionGroupInfo cgi;

	private String mTag;

	private String user_role = "ROLE_ADMIN";
	private LinearLayout /* ll_post_in_group, */ll_noti_join_group;
	private TextView txt_gr_name;

	private RecyclerView rv_group_posts;
	private SwipeRefreshLayout srl_group_posts;
	private PostAdapter postAdapter;
	private ArrayList<GroupPostInfo> gr_posts;

	private ImageView iv_group_back_btn, iv_group_setting_btn;
	private AppCompatButton btn_join_group;

	private Thread newThread;

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
		//User.GetInstance().IsAdmin(cgi.id);


	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_group, container, false);

		presenter = new GetPostsPresenter(this);
		InitView(view);
		InitListener();
		InitData();

		return view;
	}

	public void InitView(View view) {

		txt_gr_name = view.findViewById(R.id.txt_gr_name);

//		ll_post_in_group = view.findViewById(R.id.ll_post_in_group);
		ll_noti_join_group = view.findViewById(R.id.ll_noti_join_group);
		iv_group_back_btn = view.findViewById(R.id.iv_group_back_btn);
		iv_group_setting_btn = view.findViewById(R.id.iv_group_setting_btn);
		btn_join_group = view.findViewById(R.id.btn_join_group);

		//Init RecyclerView and SwipeRefreshLayout
		srl_group_posts = view.findViewById(R.id.srl_group_posts);
		rv_group_posts = view.findViewById(R.id.rv_group_posts);
		LinearLayoutManager llm = new LinearLayoutManager(getContext());
		llm.setReverseLayout(true);
		llm.setStackFromEnd(true);
		rv_group_posts.setLayoutManager(llm);

		gr_posts = new ArrayList<>();
		postAdapter = new PostAdapter(getContext(), gr_posts);
		rv_group_posts.setAdapter(postAdapter);

		if (mTag.equals("TAQ_GROUP_NOT_JOIN")) {

			btn_join_group.setVisibility(View.VISIBLE);
			ll_noti_join_group.setVisibility(View.VISIBLE);
//			ll_post_in_group.setVisibility(View.INVISIBLE);
			rv_group_posts.setVisibility(View.GONE);
			iv_group_setting_btn.setVisibility(View.GONE);
		}

	}

	public void InitData(){
		txt_gr_name.setText(cgi.name);
	}

	public void LoadGroupPosts(){
		newThread = new Thread(new Runnable() {
			@Override
			public void run() {
				presenter.HandleGetPosts(cgi);
			}
		});

		newThread.start();

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
				mTag = "TAQ_GROUP_REQUESTING";
				btn_join_group.setText("Requesting");

				btn_join_group.setVisibility(View.GONE);
				ll_noti_join_group.setVisibility(View.GONE);
//				ll_post_in_group.setVisibility(View.VISIBLE);
			}
		});

		iv_group_setting_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				GroupUserManageBottomSheet bottomSheet = new GroupUserManageBottomSheet();
				assert getFragmentManager() != null;
				bottomSheet.show(getFragmentManager(), "user_settings");
			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (newThread != null && newThread.isAlive()){newThread.interrupt();}
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
}
