package com.example.confession.views;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.confession.R;

public class HomePageActivity extends AppCompatActivity {

	private ActionBar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);

		ImageView v0 = findViewById(R.id.newfeed);
		v0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Fragment selectedFragment = null;
				selectedFragment = new fragment_newfeed();
				getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

			}

		});

		ImageView v1 = findViewById(R.id.search);
		v1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Fragment selectedFragment = null;
				selectedFragment = new fragment_search();
				getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

			}

		});

		ImageView v2 = findViewById(R.id.add);
		v2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Fragment selectedFragment = null;
				selectedFragment = new fragment_add();
				getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

			}

		});
		ImageView v3 = findViewById(R.id.follow);
		v3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Fragment selectedFragment = null;
				selectedFragment = new fragment_follow();
				getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

			}

		});
		ImageView v4 = findViewById(R.id.profie);
		v4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Fragment selectedFragment = null;
				selectedFragment = new fragment_profie();
				getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

			}

		});
	};
}