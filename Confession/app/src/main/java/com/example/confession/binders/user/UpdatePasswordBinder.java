package com.example.confession.binders.user;

import com.example.confession.models.behaviors.User;

public interface UpdatePasswordBinder {

	interface View {

		void OnUpdatePasswordSuccess(User user);
		void OnUpdatePasswordFailure(String error);
	}


