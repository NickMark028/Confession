package com.example.confession.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.confession.R;
import com.example.confession.adapters.PostAdapter;
import com.example.confession.binders.group.GetPostsBinder;
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
	private PostAdapter adapter;
	private ConfessionGroupInfo cgi;

	private String mTag;

	private String user_role = "ROLE_ADMIN";
	private LinearLayout /* ll_post_in_group, */ll_noti_join_group;
	private ListView lv_posts;
	private ImageView iv_group_back_btn, iv_group_setting_btn;
	private AppCompatButton btn_join_group;

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

		return view;
	}

	public void InitView(View view) {

		lv_posts = view.findViewById(R.id.lv_posts);
//		ll_post_in_group = view.findViewById(R.id.ll_post_in_group);
		ll_noti_join_group = view.findViewById(R.id.ll_noti_join_group);
		iv_group_back_btn = view.findViewById(R.id.iv_group_back_btn);
		iv_group_setting_btn = view.findViewById(R.id.iv_group_setting_btn);
		btn_join_group = view.findViewById(R.id.btn_join_group);

		if (mTag.equals("TAQ_GROUP_NOT_JOIN")) {

			btn_join_group.setVisibility(View.VISIBLE);
			ll_noti_join_group.setVisibility(View.VISIBLE);
//			ll_post_in_group.setVisibility(View.INVISIBLE);
			lv_posts.setVisibility(View.INVISIBLE);
		} else {

			new Thread(() -> presenter.HandleGetPosts(cgi));
		}
	}

	public void InitListener() {
		iv_group_back_btn.setOnClickListener(v -> getFragmentManager().popBackStack());

		btn_join_group.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("SetTextI18n")
			@Override
			public void onClick(View v) {
				mTag = "TAQ_GROUP_REQUESTING";
				btn_join_group.setText("Requesting");

				btn_join_group.setVisibility(View.GONE);
				ll_noti_join_group.setVisibility(View.GONE);
//				ll_post_in_group.setVisibility(View.VISIBLE);
				lv_posts.setVisibility(View.VISIBLE);

				new Thread(() -> presenter.HandleGetPosts(cgi));
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
	public void OnGetPostsSuccess(ArrayList<GroupPostInfo> posts) {

		// TODO UI adapter update?
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
//				adapter = new PostAdapter(getContext(), posts);
//				lv_posts.setAdapter(adapter);
			}
		});
	}

	@Override
	public void OnGetPostsFailure(String error) {
		Log.i("Failure", "GetPosts");
	}
}
