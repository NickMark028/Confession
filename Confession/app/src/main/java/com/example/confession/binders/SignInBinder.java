package com.example.confession.binders;

public interface SignInBinder {

	interface View {

		void OnLoginSuccess();
		void OnLoginFailure(int error_code);
	}

	interface Presenter {

		void HandleLogin(String username, String password);
	}
}
