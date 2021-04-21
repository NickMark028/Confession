package com.example.confession.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.adapters.ViewPagerAdapter;
import com.example.confession.binders.JoinedGroupsTabBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.JoinedGroupsTabPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

public class ProfileGroupsFragment extends Fragment implements JoinedGroupsTabBinder.View {

	private JoinedGroupsTabBinder.Presenter presenter;
	private String mTag;
	private ViewPager profile_group_viewpager;
	private TextView profile_txt_username_click;
	private ImageButton profile_group_back_btn;
	private TabLayout group_tab_layout;
	private Thread newThread;

	private FragmentStatePagerAdapter adapterViewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		presenter = new JoinedGroupsTabPresenter(this);
		mTag = this.getTag();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_profile_groups, container, false);

		profile_group_back_btn = view.findViewById(R.id.profile_group_back_btn);
		group_tab_layout = view.findViewById(R.id.group_tab_layout);
		profile_group_viewpager = view.findViewById(R.id.profile_group_viewpager);
		profile_txt_username_click = view.findViewById(R.id.profile_txt_username_click);

		adapterViewPager = new ViewPagerAdapter(getChildFragmentManager());
		profile_group_viewpager.setAdapter(adapterViewPager);
		profile_group_viewpager.setOffscreenPageLimit(2);

		group_tab_layout.setupWithViewPager(profile_group_viewpager);
		profile_txt_username_click.setText(User.GetInstance().GetBasicUserInfo().username);

		InitListener();
		InitTabLayout();

		return view;
	}

	private void CallPresenter(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				presenter.HandleGetFollowedGroups();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				presenter.HandleGetCreatedGroups();
			}
		}).start();
	}

	public void InitListener() {

		profile_group_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				Toast.makeText(getContext(), "Frag-Pos-" + position, Toast.LENGTH_SHORT).show();
				//CallPresenter(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		group_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				Toast.makeText(getContext(), tab.getText(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});

		profile_group_back_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				assert getFragmentManager() != null;
				getFragmentManager().popBackStack();

			}
		});
	}


	public void InitTabLayout() {
		if (mTag.equals("TAG_JOINED_GROUP")) {
			group_tab_layout.getTabAt(1).select();
		}
	}



	@Override
	public void OnGetFollowedGroupsSuccess(ArrayList<ConfessionGroupInfo> groups) {

	}

	@Override
	public void OnGetFollowedGroupsFailure(String error) {

	}

	@Override
	public void OnGetCreatedGroupsSuccess(ArrayList<ConfessionGroupInfo> groups) {

	}

	@Override
	public void OnGetCreatedGroupsFailure(String error) {

	}

	@Override
	public void OnLeaveGroupSuccess(ConfessionGroupInfo group) {

	}

	@Override
	public void OnLeaveGroupFailure(String error) {

	}


}
