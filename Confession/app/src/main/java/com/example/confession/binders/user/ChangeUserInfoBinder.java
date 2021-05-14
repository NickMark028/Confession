package com.example.confession.binders.user;

import com.example.confession.models.behaviors.User;

public interface ChangeUserInfoBinder {

	interface View {

		void OnChangeUserInfoSuccess(User user);
		void OnChangeUserInfoFailure(String error);
	}

	interface Presenter {

		void HandleChangeUserInfo(String fullname, String email, String phone);
	}
}
