package com.example.confession.binders.user;

import com.example.confession.models.behaviors.User;

public interface ChangePasswordBinder {

	interface View {

		void OnChangePasswordSuccess(User user);
		void OnChangePasswordFailure(String error);
	}

	interface Presenter {

		void HandleChangePassword(String old_pass, String new_pass);
	}
}
