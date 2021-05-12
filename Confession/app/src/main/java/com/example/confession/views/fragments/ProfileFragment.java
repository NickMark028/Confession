package com.example.confession.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.models.behaviors.User;
import com.example.confession.views.activities.ChangePasswordActivity;
import com.example.confession.views.activities.CreateGroupActivity;
import com.example.confession.views.activities.UpdateProfileActivity;
import com.example.confession.views.bottomsheet.ProfileCreateNewBottomSheet;
import com.example.confession.views.bottomsheet.ProfileUsernameBottomSheet;

public class ProfileFragment extends Fragment {

	// IN-USE VARIABLES

	private TextView profile_txt_username_click;
	private ImageButton profile_open_post_btn;
	private LinearLayout profile_joined_group, profile_your_group,
			profile_edit_account_btn, profile_change_pass_btn, profile_faq_btn, profile_contact_us_btn;
	private TextView profile_fullname, profile_email, profile_phone;

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
		InitListener();
	}

	private void InitPresenter() {
		//presenter = new ProfilePresenter(this);
	}

	private void InitView() {
		profile_txt_username_click = getView().findViewById(R.id.profile_txt_username_click);
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
				ProfileUsernameBottomSheet bottomSheet = new ProfileUsernameBottomSheet();
				assert getFragmentManager() != null;
				bottomSheet.show(getFragmentManager(), "username");
			}
		});


		profile_open_post_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Toast.makeText(getContext(), "bottom sheet open post", Toast.LENGTH_SHORT).show();
				ProfileCreateNewBottomSheet bottomSheet = new ProfileCreateNewBottomSheet();
				assert getFragmentManager() != null;
				bottomSheet.show(getFragmentManager(), "create_new");

				int result = bottomSheet.GetResult();

				if (result == 1) {
					Intent mIntent = new Intent(getContext().getApplicationContext(), CreateGroupActivity.class);
					startActivity(mIntent);
				}
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
				Toast.makeText(getContext(), "Edit account", Toast.LENGTH_SHORT).show();
				Intent myIntent = new Intent(getContext().getApplicationContext(), UpdateProfileActivity.class);
				startActivity(myIntent);
			}
		});

		profile_change_pass_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getContext(), "Change password", Toast.LENGTH_SHORT).show();
				Intent myIntent = new Intent(getContext().getApplicationContext(), ChangePasswordActivity.class);
				startActivity(myIntent);
			}
		});

		profile_faq_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getContext(), "FAQ", Toast.LENGTH_SHORT).show();

			}
		});

		profile_contact_us_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getContext(), "Contact us", Toast.LENGTH_SHORT).show();
			}
		});
	}
}