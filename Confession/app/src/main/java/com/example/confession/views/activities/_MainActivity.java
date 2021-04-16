package com.example.confession.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		BasicUserInfo basic_info = new BasicUserInfo("admin", "Admin name");
//		UserInfo info = new UserInfo(basic_info, "test@email.com", "0xx xxx xxx1");
//		User user = new User(info);
//		Bundle bundle = user.ToBundle();
//		User new_user = User.From(bundle);
//
//		Log.i("Data", new_user.toString());


//		ConfessionGroup g = new ConfessionGroup(new ConfessionGroupInfo("60505ccec951fc27083de447", "", "", ""));
//		g.GetPosts(new UserInfo(new BasicUserInfo("","","",""),"","",""));

		setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);

//		Intent intent = new Intent(this, CommentActivity.class);

		Intent intent = new Intent(this, SignInActivity.class);
		startActivity(intent);
	}
}
