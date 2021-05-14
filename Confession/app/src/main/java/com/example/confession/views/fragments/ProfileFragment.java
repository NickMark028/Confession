package com.example.confession.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.user.CreatedGroupsBinder;
import com.example.confession.binders.user.FollowedGroupsBinder;
import com.example.confession.binders.user.SignOutBinder;
import com.example.confession.presenters.user.SignOutPresenter;
import com.example.confession.listener.BottomSheetCreateNewListener;
import com.example.confession.listener.BottomSheetLogoutListener;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.user.CreatedGroupsPresenter;
import com.example.confession.presenters.user.FollowedGroupsPresenter;
import com.example.confession.views.activities.AddPostActivity;
import com.example.confession.views.activities.ChangePasswordActivity;
import com.example.confession.views.activities.CreateGroupActivity;
import com.example.confession.views.activities.SignInActivity;
import com.example.confession.views.activities.UpdateProfileActivity;
import com.example.confession.views.bottomsheet.ProfileCreateNewBottomSheet;
import com.example.confession.views.bottomsheet.ProfileUsernameBottomSheet;

import java.util.ArrayList;

public class ProfileFragment extends Fragment
		implements
		FollowedGroupsBinder.View,
		CreatedGroupsBinder.View,
		BottomSheetCreateNewListener,
		BottomSheetLogoutListener,
		SignOutBinder.View {

	// IN-USE VARIABLES
	private final SignOutBinder.Presenter presenter;

	private ProfileCreateNewBottomSheet bottomSheetCreateNew;
	private ProfileUsernameBottomSheet bottomSheetLogout;

	private FollowedGroupsBinder.Presenter followedGroupPresenter;
	private CreatedGroupsBinder.Presenter createdGroupPresenter;

	private TextView profile_txt_username_click;
	private ImageButton profile_open_post_btn;
	private LinearLayout profile_joined_group, profile_your_group,
			profile_edit_account_btn, profile_change_pass_btn, profile_faq_btn, profile_contact_us_btn;
	private TextView profile_fullname, profile_email, profile_phone, txt_ygroup_count, txt_fgroup_count;
	private Thread createdGroupThread, followGroupThread;

	public ProfileFragment() {
		presenter = new SignOutPresenter(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_profie, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		InitPresenter();
		InitView();
		InitBottomSheet();
		InitListener();

		HandleGetFollowedGroup();
		HandleGetCreatedGroup();
	}

	private void InitPresenter() {
		//presenter = new ProfilePresenter(this);
		followedGroupPresenter = new FollowedGroupsPresenter(this);
		createdGroupPresenter = new CreatedGroupsPresenter(this);
	}

	private void InitBottomSheet() {
		bottomSheetCreateNew = new ProfileCreateNewBottomSheet(this);
		bottomSheetLogout = new ProfileUsernameBottomSheet(this);
	}

	private void HandleGetFollowedGroup() {
		try {
			followGroupThread = new Thread(new Runnable() {
				@Override
				public void run() {
					followedGroupPresenter.HandleGetFollowedGroups();
				}
			});

			followGroupThread.start();
		} catch (Exception e) {
			Log.e("In Follow Thread", e.getMessage());
		}
	}

	private void HandleGetCreatedGroup() {
		try {
			createdGroupThread = new Thread(new Runnable() {
				@Override
				public void run() {
					createdGroupPresenter.HandleGetCreatedGroups();
				}
			});

			createdGroupThread.start();
		} catch (Exception e) {
			Log.e("In Follow Thread", e.getMessage());
		}
	}

	private void InitView() {
		profile_txt_username_click = getView().findViewById(R.id.profile_txt_username_click);
		txt_ygroup_count = getView().findViewById(R.id.txt_ygroup_count);
		txt_fgroup_count = getView().findViewById(R.id.txt_fgroup_count);
		profile_fullname = getView().findViewById(R.id.profile_fullname);
		profile_email = getView().findViewById(R.id.profile_email);
		profile_phone = getView().findViewById(R.id.profile_phone);

		profile_open_post_btn = getView().findViewById(R.id.profile_open_post_btn);
		profile_joined_group = getView().findViewById(R.id.profile_joined_group);
		profile_your_group = getView().findViewById(R.id.profile_your_group);
		profile_edit_account_btn = getView().findViewById(R.id.profile_edit_account_btn);
		profile_change_pass_btn = getView().findViewById(R.id.profile_change_pass_btn);
		profile_faq_btn = getView().findViewById(R.id.profile_faq_btn);
		profile_contact_us_btn = getView().findViewById(R.id.profile_contact_us_btn);

		//Set up data
		profile_txt_username_click.setText(User.GetInstance().GetBasicUserInfo().username);
		profile_fullname.setText(User.GetInstance().GetBasicUserInfo().name);
		profile_email.setText(User.GetInstance().GetUserInfo().email);
		profile_phone.setText(User.GetInstance().GetUserInfo().phone);

	}

	private void InitListener() {

		profile_txt_username_click.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//presenter.HandleLogin(si_username.getText().toString(), si_password.getText().toString());
				assert getFragmentManager() != null;
				bottomSheetLogout.show(getFragmentManager(), "username");
			}
		});


		profile_open_post_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Toast.makeText(getContext(), "bottom sheet open post", Toast.LENGTH_SHORT).show();
				assert getFragmentManager() != null;
				bottomSheetCreateNew.show(getFragmentManager(), "create_new");

			}
		});

		profile_joined_group.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Toast.makeText(getContext(), "Joined group", Toast.LENGTH_SHORT).show();

				Fragment fragment = new ProfileGroupsFragment();

				assert getFragmentManager() != null;
				getFragmentManager()
						.beginTransaction()
						.replace(R.id.fragment_container, fragment, "TAG_JOINED_GROUP")
						.addToBackStack("TAG_JOINED_GROUP")
						.commit();
			}
		});

		profile_your_group.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Toast.makeText(getContext(), "Your group", Toast.LENGTH_SHORT).show();
				Fragment fragment = new ProfileGroupsFragment();

				assert getFragmentManager() != null;
				getFragmentManager()
						.beginTransaction()
						.replace(R.id.fragment_container, fragment, "TAG_YOUR_GROUP")
						.addToBackStack("TAG_YOUR_GROUP")
						.commit();
			}
		});

		profile_edit_account_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Toast.makeText(getContext(), "Edit account", Toast.LENGTH_SHORT).show();
				Intent myIntent = new Intent(getContext().getApplicationContext(), UpdateProfileActivity.class);
				startActivity(myIntent);
			}
		});

		profile_change_pass_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Toast.makeText(getContext(), "Change password", Toast.LENGTH_SHORT).show();
				Intent myIntent = new Intent(getContext().getApplicationContext(), ChangePasswordActivity.class);
				startActivity(myIntent);
			}
		});

		profile_faq_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getContext(), "New feature", Toast.LENGTH_SHORT).show();
			}
		});

		profile_contact_us_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getContext(), "New feature", Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void ReUpdateUserData() {
		profile_txt_username_click.setText(User.GetInstance().GetBasicUserInfo().username);
		profile_fullname.setText(User.GetInstance().GetBasicUserInfo().name);
		profile_email.setText(User.GetInstance().GetUserInfo().email);
		profile_phone.setText(User.GetInstance().GetUserInfo().phone);
	}

	@Override
	public void onResume() {
		super.onResume();
		ReUpdateUserData();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (followGroupThread != null && followGroupThread.isAlive()) {
			followGroupThread.interrupt();
		}

		if (createdGroupThread != null && createdGroupThread.isAlive()) {
			createdGroupThread.interrupt();
		}
	}

	@Override
	public void onButtonCreatePostClicked(int result) {
		Intent addPostIntent = new Intent(getContext(), AddPostActivity.class);
		startActivity(addPostIntent);
	}

	@Override
	public void onButtonCreateGroupClicked(int result) {
		Intent createGroupIntent = new Intent(getContext(), CreateGroupActivity.class);
		startActivity(createGroupIntent);
	}

	@Override
	public void onButtonLogoutClicked(int result) {

		presenter.HandleSignOut(getActivity());
	}

	@Override
	public void OnGetCreatedGroupsSuccess(ArrayList<ConfessionGroupInfo> groups) {
		if (getActivity() == null) {
			return;
		}

		int count = groups.size();
		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				txt_ygroup_count.setText(Integer.toString(count));
			}
		});
	}

	@Override
	public void OnGetCreatedGroupsFailure(String error) {
		// Do nothing
	}

	@Override
	public void OnGetFollowedGroupsSuccess(ArrayList<ConfessionGroupInfo> groups) {
		if (getActivity() == null) {
			return;
		}

		int count = groups.size();
		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				txt_fgroup_count.setText(Integer.toString(count));
			}
		});
	}

	@Override
	public void OnGetFollowedGroupsFailure(String error) {
		// Do nothing
	}


	@Override
	public void OnSignOutSuccess() {

		Intent myIntent = new Intent(getActivity().getApplicationContext(), SignInActivity.class);
		startActivity(myIntent);
		getActivity().finish();
	}

	@Override
	public void OnSignOutFailure(String error) {

	}
}