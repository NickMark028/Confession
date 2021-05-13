package com.example.confession.views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.confession.listener.BottomSheetListener;
import com.example.confession.listener.BottomSheetManageGroupListener;
import com.example.confession.models.behaviors.User;
import com.example.confession.service.MyFirebasePushNotificationService;
import com.example.confession.views.fragments.FollowFragment;
import com.example.confession.views.fragments.NewfeedsFragment;
import com.example.confession.views.fragments.ProfileFragment;
import com.example.confession.views.fragments.GroupSearchFragment;

import com.example.confession.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class HomePageActivity extends AppCompatActivity implements BottomSheetListener {

	private ActionBar toolbar;
	private int mPrevNavItem = 0;
	private int mCurrentNavItem = 100083;
	BottomNavigationView bottomNavigationView;

	HashMap<Integer, Fragment> frag_map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);

		frag_map = new HashMap<>();
		frag_map.put(R.id.navigation_home, new NewfeedsFragment());
		frag_map.put(R.id.navigation_search, new GroupSearchFragment());
//		frag_map.put(R.id.navigation_add_post, new NewfeedFragment());
		frag_map.put(R.id.navigation_heart, new FollowFragment());
		frag_map.put(R.id.navigation_profile, new ProfileFragment());

		toolbar = getSupportActionBar();
		bottomNavigationView = findViewById(R.id.navigation_bar);

		bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
			if (mCurrentNavItem == item.getItemId()) {
				return true;
			}

			Fragment frag = frag_map.get(item.getItemId());
			if (frag != null)
				SetFragment(frag);
			else {
				Intent myIntent = new Intent(getApplicationContext(), AddPostActivity.class);
				startActivity(myIntent);
			}

			mPrevNavItem = mCurrentNavItem;
			mCurrentNavItem = item.getItemId();
			item.setChecked(true);
			return true;
		});

		SetFragment(new NewfeedsFragment());

		InitService();
		firebaseSubscribeTopic();
	}

	public void InitService(){
		Intent intentBGService = new Intent(this, MyFirebasePushNotificationService.class);
		startService(intentBGService);
	}

	private void firebaseSubscribeTopic(){
		FirebaseMessaging.getInstance().subscribeToTopic("pending")
				.addOnCompleteListener(new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						String msg = "Done";

						if(!task.isSuccessful()){
							msg = "Failed";
						}
					}
				});
	}

	@Override
	protected void onResume() {
		super.onResume();
		bottomNavigationView.setSelectedItemId(mPrevNavItem);
	}

	private void SetFragment(Fragment selectedFragment) {
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.fragment_container, selectedFragment)
				.commit();
	}

	//BottomListener method
	@Override
	public void onButtonClicked(String text) {
		if (text.equals("logout")) {
			Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();

			SharedPreferences share = getSharedPreferences("USERDATA",MODE_PRIVATE);
			SharedPreferences.Editor editor = share.edit();
			editor.putString("token", "");
			editor.apply();

			Intent myIntent = new Intent(getApplicationContext(), SignInActivity.class);
			startActivity(myIntent);
			finish();
		}

		if (text.equals("create_post")) {
			Toast.makeText(getApplicationContext(), "Create Post", Toast.LENGTH_SHORT).show();

			View view = bottomNavigationView.findViewById(R.id.navigation_add_post);
			view.performClick();
		} else {
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
		}
	}

}
