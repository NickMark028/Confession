package com.example.confession.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.confession.binders.BottomSheetListener;
import com.example.confession.models.behaviors.User;
import com.example.confession.presenters.SearchGroupPresenter;
import com.example.confession.views.fragments.FollowFragment;
import com.example.confession.views.fragments.NewfeedsFragment;
import com.example.confession.views.fragments.ProfileFragment;
import com.example.confession.views.fragments.SearchFragment;

import com.example.confession.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class HomePageActivity extends AppCompatActivity implements BottomSheetListener {

	private User user;

	private ActionBar toolbar;
	private int mPrevNavItem = 0;
	private int mCurrentNavItem = 100083;
	BottomNavigationView bottomNavigationView;

	HashMap<Integer, Fragment> frag_map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);

//		user = User.From(savedInstanceState);

		frag_map = new HashMap<>();
		frag_map.put(R.id.navigation_home, new NewfeedsFragment());
		frag_map.put(R.id.navigation_search, new SearchFragment());
//		frag_map.put(R.id.navigation_add_post, new NewfeedFragment());
		frag_map.put(R.id.navigation_heart, new FollowFragment());
		frag_map.put(R.id.navigation_profile, new ProfileFragment());

		toolbar = getSupportActionBar();
		bottomNavigationView = findViewById(R.id.navigation_bar);

		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

//				switch (item.getItemId()) {
//					// TODO change to newsfeed agian
//					case R.id.navigation_home:
//						SetFragment(new NewfeedFragment());
//						break;
//					case R.id.navigation_search:
//						SetFragment(new SearchFragment());
//						break;
//					case R.id.navigation_add_post:
//						//SetFragment(new AddFragment());
//						Intent myIntent = new Intent(getApplicationContext(), AddPostActivity.class);
//						startActivity(myIntent);
//						break;
//					case R.id.navigation_heart:
//						SetFragment(new FollowFragment());
//						break;
//					case R.id.navigation_profile:
//						SetFragment(new ProfileFragment());
//						break;
//				}
				mPrevNavItem = mCurrentNavItem;
				mCurrentNavItem = item.getItemId();
				item.setChecked(true);
				return true;
			}
		});

		SetFragment(new NewfeedsFragment());

	}

	@Override
	protected void onResume() {
		super.onResume();
		bottomNavigationView.setSelectedItemId(mPrevNavItem);
	}

	private void SetFragment(Fragment selectedFragment) {
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
	}

	//BottomListener method
	@Override
	public void onButtonClicked(String text) {
		if (text.equals("logout")) {
			Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();

			Intent myIntent = new Intent(getApplicationContext(), SignInActivity.class);
			startActivity(myIntent);
			finish();
		}

		if (text.equals("create_post")) {
			Toast.makeText(getApplicationContext(), "Create Post", Toast.LENGTH_SHORT).show();

			View view = bottomNavigationView.findViewById(R.id.navigation_add_post);
			view.performClick();
		}

		if (text.equals("create_group")) {
			Toast.makeText(getApplicationContext(), "Create Group", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
		}


	}
}
