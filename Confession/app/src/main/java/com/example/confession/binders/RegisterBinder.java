package com.example.confession.binders;

import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.UserInfo;

public interface RegisterBinder {

	interface View {

		void OnRegisterSuccess(User user);
		void OnRegisterFailure(int error_code);
	}

	interface Presenter {

		void HandleRegister(UserInfo user);
	}
}
