package com.example.confession.views.activities;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.confession.views.fragments.AddFragment;
import com.example.confession.views.fragments.FollowFragment;
import com.example.confession.views.fragments.NewfeedFragment;
import com.example.confession.views.fragments.ProfileFragment;
import com.example.confession.views.fragments.SearchFragment;

import com.example.confession.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageActivity extends AppCompatActivity {

	private ActionBar toolbar;
	private int mCurrentNavItem = 100083;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);



		toolbar = getSupportActionBar();
		BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bar);

		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				if(mCurrentNavItem == item.getItemId()){
					return true;
				}

				switch (item.getItemId()){
					case R.id.navigation_home:
						SetFragment(new NewfeedFragment());
						break;
					case R.id.navigation_search:
						SetFragment(new SearchFragment());
						break;
					case R.id.navigation_add_post:
						//SetFragment(new AddFragment());
						Intent myIntent = new Intent(getApplicationContext(), AddPostActivity.class);
						startActivity(myIntent);
						break;
					case R.id.navigation_heart:
						SetFragment(new FollowFragment());
						break;
					case R.id.navigation_profile:
						SetFragment(new ProfileFragment());
						break;
				}

				mCurrentNavItem = item.getItemId();
				item.setChecked(true);
				return true;
			}
		});

		SetFragment(new NewfeedFragment());

	};

	private void SetFragment(Fragment selectedFragment){
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
	}
}