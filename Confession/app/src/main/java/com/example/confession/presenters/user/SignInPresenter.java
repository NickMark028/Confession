package com.example.confession.presenters.user;

import com.example.confession.binders.user.SignInBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.utilities.Hashing;

public class SignInPresenter implements SignInBinder.Presenter {

	private final SignInBinder.View view;

	public SignInPresenter(SignInBinder.View view)
	{
		this.view = view;
	}

	@Override
	public void HandleLogin(String username, String password) {

		int error_code = 0;
		if (username.isEmpty())
			error_code |= SignInBinder.ERROR_EMPTY_USERNAME;
		if (password.isEmpty())
			error_code |= SignInBinder.ERROR_EMPTY_PASSWORD;

		if (error_code > 0)
		{
			view.OnLoginFailure(error_code);
			return;
		}

		User user = User.Login(username, Hashing.SHA512(password));
		if (user != null)
			view.OnLoginSuccess(user);
		else
			view.OnLoginFailure(SignInBinder.ERROR_USER_NOT_EXISTS);
	}
	public void HandleLogin(String token)
	{
		User user = User.checkToken(token);

		if (user != null)
			view.OnLoginSuccess(user);
		else
			view.OnLoginFailure(SignInBinder.ERROR_USER_NOT_EXISTS);
	}
}
