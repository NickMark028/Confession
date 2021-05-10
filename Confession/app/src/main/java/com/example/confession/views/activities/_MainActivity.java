package com.example.confession.views.activities;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.example.confession.R;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.UserInfo;
import com.example.confession.service.MyFirebasePushNotificationService;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class _MainActivity extends Activity {

	private SharedPreferences share;
	private String token;
	private Thread newThread;
	private Handler mWait = new Handler();
	private TextView app_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		app_name = findViewById(R.id.app_name);

		setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);

		share = this.getPreferences(MODE_PRIVATE);
		token = share.getString("token", "");

		if(token.isEmpty()){
			mWait.postDelayed(new Runnable() {
				@Override
				public void run() {
					try{
						Intent intent = new Intent(_MainActivity.this, SignInActivity.class);
						ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
								_MainActivity.this,
								new Pair<View, String>(app_name, "confession"));

						startActivity(intent, options.toBundle());
					}catch (Exception exc){exc.printStackTrace();}
				}
			}, 5000);
		}
	}



	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		mWait.removeCallbacksAndMessages(null);
	}
}
