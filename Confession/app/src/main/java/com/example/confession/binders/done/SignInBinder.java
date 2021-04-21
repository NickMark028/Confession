package com.example.confession.binders.done;

import android.content.Context;

import com.example.confession.models.behaviors.User;

public interface SignInBinder {

	interface View {

		void OnLoginSuccess(User user);
		void OnLoginFailure(int error_code);
	}

	interface Presenter {

		void HandleLogin(String username, String password);
	}
}
