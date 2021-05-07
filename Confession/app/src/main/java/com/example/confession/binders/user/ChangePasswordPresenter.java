package com.example.confession.binders.user;

import com.example.confession.models.behaviors.User;

public class ChangePasswordPresenter implements ChangePasswordBinder.Presenter {

	private final ChangePasswordBinder.View view;

	public ChangePasswordPresenter(ChangePasswordBinder.View view) {
		this.view = view;
	}


	@Override
	public void HandleChangePassword(String old_pass, String new_pass) {

		User user = User.GetInstance();

//		if (user.)
	}
}
