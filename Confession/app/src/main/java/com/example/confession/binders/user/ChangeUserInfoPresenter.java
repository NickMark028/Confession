package com.example.confession.binders.user;

import com.example.confession.models.behaviors.User;

public class ChangeUserInfoPresenter implements ChangeUserInfoBinder.Presenter {

	private final ChangePasswordBinder.View view;

	public ChangeUserInfoPresenter(ChangePasswordBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleChangeUserInfo(String fullname, String email, String phone) {

		User user = User.GetInstance();

	}
}
