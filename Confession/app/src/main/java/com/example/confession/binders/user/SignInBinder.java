package com.example.confession.binders.user;

import com.example.confession.models.behaviors.User;

public interface SignInBinder {

	int ERROR_EMPTY_USERNAME = 1 << 0;
	int ERROR_EMPTY_PASSWORD = 1 << 1;
	int ERROR_USER_NOT_EXISTS = 1 << 31;

	interface View {

		void OnLoginSuccess(User user);
		void OnLoginFailure(int error_code);
	}

	interface Presenter {

		void HandleLogin(String username, String password);
		void HandleLogin(String token);
	}
}
