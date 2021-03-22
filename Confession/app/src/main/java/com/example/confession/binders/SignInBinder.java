package com.example.confession.binders;

public interface SignInBinder {

	interface View {

		void LoginSuccessfully();
		void LoginFailure();
	}

	interface Presenter {

		void HandleLogin(String username, String password);
	}
}
