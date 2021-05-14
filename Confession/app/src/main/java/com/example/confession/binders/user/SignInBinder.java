package com.example.confession.binders.user;

import com.example.confession.models.behaviors.User;

import java.io.Serializable;

//enum SignInErrorMask {
//
//
//
//	private int value;
//	public int GetValue() {
//		return value
//	}
//
//	SignInErrorMask()
//}

public interface SignInBinder {

//	final int

	interface View {

		void OnLoginSuccess(User user);

		void OnLoginFailure(String error);
	}

	interface Presenter {
		void HandleLogin(String username, String password);

		void HandleLogin(String token);
	}
}
