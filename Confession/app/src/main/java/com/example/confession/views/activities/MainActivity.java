package com.example.confession.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.UserInfo;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ConfessionGroup g = new ConfessionGroup(new ConfessionGroupInfo("60505ccec951fc27083de447", "", "", ""));
		g.GetPosts(new UserInfo(new BasicUserInfo("","",""),"","",""));

		//Intent intent = new Intent(this, SignInActivity.class);
		//startActivity(intent);
	}
}
