package com.example.confession.presenters.user;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.confession.binders.user.SignOutBinder;

import static android.content.Context.MODE_PRIVATE;

public class SignOutPresenter implements SignOutBinder.Presenter {

	private final SignOutBinder.View view;

	public SignOutPresenter(SignOutBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleSignOut(Activity activity) {

		SharedPreferences share = activity.getSharedPreferences("USERDATA",MODE_PRIVATE);
		SharedPreferences.Editor editor = share.edit();
		editor.putString("token", "");
		editor.apply();

		view.OnSignOutSuccess();
	}
}
