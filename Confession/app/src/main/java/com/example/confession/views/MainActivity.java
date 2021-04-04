package com.example.confession.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.confession.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		Intent intent = new Intent(this, SignInActivity.class);
		startActivity(intent);
	}
}