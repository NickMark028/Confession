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
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.user.SignInBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.UserInfo;
import com.example.confession.presenters.user.SignInPresenter;
import com.example.confession.service.MyFirebasePushNotificationService;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class _MainActivity extends Activity implements SignInBinder.View {

	private final SignInBinder.Presenter presenter;

	private SharedPreferences share;
	private String token;
	private Handler mWait = new Handler();
	private Thread thread;
	private TextView app_name;

	public _MainActivity() {

		presenter = new SignInPresenter(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		app_name = findViewById(R.id.app_name);

		setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);

		share = getSharedPreferences("USERDATA",MODE_PRIVATE);
		token = share.getString("token", "");

//		Log.e("token cua t dau ???", token);

		if (token.length()<10) {
			mWait.postDelayed(new Runnable() {
				@Override
				public void run() {
					StartLogin();
				}
			}, 3000);    // TODO
		} else {
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					presenter.HandleLogin(token);
				}
			});
			thread.start();
		}
	}

	private void StartLogin() {
		Intent intent = new Intent(_MainActivity.this, SignInActivity.class);
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
				_MainActivity.this,
				new Pair<>(app_name, "confession"));

		startActivity(intent, options.toBundle());
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		mWait.removeCallbacksAndMessages(null);
	}

	@Override
	public void OnLoginSuccess(User user) {

		Intent intent = new Intent(_MainActivity.this, HomePageActivity.class);
		startActivity(intent);

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
//				Toast.makeText(getApplicationContext(), "Check Token thanh cong", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void OnLoginFailure(String error) {
		runOnUiThread(() -> {
//			Toast.makeText(getApplicationContext(), "Check Token that cmn bai", Toast.LENGTH_SHORT).show();
			StartLogin();
		});
	}
}
