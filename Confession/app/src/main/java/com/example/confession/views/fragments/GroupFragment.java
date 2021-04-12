package com.example.confession.views.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.confession.R;
import com.example.confession.binders.GroupBinder;
import com.example.confession.views.bottomsheet.GroupUserManageBottomSheet;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupFragment extends Fragment {

//	GroupBinder.Presenter presenter = new;

	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	private String mTag;
	private String user_role = "ADMIN";
	private LinearLayout ll_post_in_group, ll_noti_join_group;
	private ImageView iv_group_back_btn, iv_group_setting_btn;
	private AppCompatButton btn_join_group;


	public GroupFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment GroupFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static GroupFragment newInstance(String param1, String param2) {
		GroupFragment fragment = new GroupFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
		mTag = this.getTag();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_group, container, false);

		InitView(view);
		InitListener();

		return view;
	}

	public void InitView(View view){
		ll_post_in_group = view.findViewById(R.id.ll_post_in_group);
		ll_noti_join_group = view.findViewById(R.id.ll_noti_join_group);
		iv_group_back_btn = view.findViewById(R.id.iv_group_back_btn);
		iv_group_setting_btn = view.findViewById(R.id.iv_group_setting_btn);
		btn_join_group = view.findViewById(R.id.btn_join_group);

		if(mTag.equals("TAQ_GROUP_NOT_JOIN")){
			btn_join_group.setVisibility(View.VISIBLE);
			ll_noti_join_group.setVisibility(View.VISIBLE);
			ll_post_in_group.setVisibility(View.INVISIBLE);
		}
	}

	public void InitListener(){
		iv_group_back_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getFragmentManager().popBackStack();
			}
		});

		btn_join_group.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTag = "TAQ_GROUP_REQUESTING";
				btn_join_group.setText("Requesting");

				btn_join_group.setVisibility(View.GONE);
				ll_noti_join_group.setVisibility(View.GONE);
				ll_post_in_group.setVisibility(View.VISIBLE);
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
}