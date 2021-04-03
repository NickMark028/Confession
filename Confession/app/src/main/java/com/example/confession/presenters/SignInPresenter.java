package com.example.confession.presenters;

import com.example.confession.binders.SignInBinder;
import com.example.confession.models.behaviors.User;

public class SignInPresenter implements SignInBinder.Presenter {

	private final SignInBinder.View view;
	private User user;

	public SignInPresenter(SignInBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleLogin(String username, String password) {

		// Testing
		user = User.Login(username, password);
		if (user != null)
			view.LoginSuccessfully();
		else
			view.LoginFailure();
	}
}
