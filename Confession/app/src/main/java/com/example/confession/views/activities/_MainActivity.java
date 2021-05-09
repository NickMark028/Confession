package com.example.confession.views.activities;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.example.confession.R;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.UserInfo;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class _MainActivity extends Activity {

	private SharedPreferences share;
	private String token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);

		share = this.getPreferences(MODE_PRIVATE);
		token = share.getString("token", "");

		Intent intent = new Intent(this, SignInActivity.class);
		startActivity(intent);
	}


}
