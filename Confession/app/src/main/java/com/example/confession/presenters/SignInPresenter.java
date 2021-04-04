package com.example.confession.presenters;

import android.content.Context;

import com.example.confession.binders.SignInBinder;
import com.example.confession.models.behaviors.User;

public class SignInPresenter implements SignInBinder.Presenter {

	Context context;
	private final SignInBinder.View view;
	private User user;

	public SignInPresenter(SignInBinder.View view, Context context)
	{
		this.context = context;
		this.view = view;
	}

	@Override
	public void HandleLogin(String username, String password) {

		// Testing
		user = User.Login(username, password,context);
		if (user != null)
			view.LoginSuccessfully();
		else
			view.LoginFailure();
	}
}
