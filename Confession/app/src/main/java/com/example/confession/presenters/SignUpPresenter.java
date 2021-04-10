package com.example.confession.presenters;

import com.example.confession.binders.SignUpBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.UserInfo;

public class SignUpPresenter implements SignUpBinder.Presenter {

	private final SignUpBinder.View view;
	private User user;

	public SignUpPresenter(SignUpBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleSignUp(String username, String email, String phone, String password, String confirm_pass) {

		// TODO Add encryption to password
		if (!password.equals(confirm_pass))
		{
			view.OnSignUpFailure(1);
			return;
		}

		// TODO Hash password
		String hash_pass = password;

		BasicUserInfo basic_user_info = new BasicUserInfo("",username, "Add a name here", "Add avatar here");
		UserInfo user_info = new UserInfo(basic_user_info, email, phone, "Authentication code here");

		if (User.Register(user_info, hash_pass))
			view.OnSignUpSuccess();
		else
			view.OnSignUpFailure(2);
	}
}
