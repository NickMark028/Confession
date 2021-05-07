package com.example.confession.presenters.user_partially_done;

import com.example.confession.binders.user.ChangePasswordBinder;
import com.example.confession.binders.user.ChangeUserInfoBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.UserInfo;

public class ChangeUserInfoPresenter implements ChangeUserInfoBinder.Presenter {

	private final ChangePasswordBinder.View view;

	public ChangeUserInfoPresenter(ChangePasswordBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleChangeUserInfo(String fullname, String email, String phone) {

		User user = User.GetInstance();

		BasicUserInfo basic_info = new BasicUserInfo(user.GetBasicUserInfo().username, fullname);
		UserInfo info = new UserInfo(basic_info, email, phone);
		user = user.UpdateUserInfo(info);

		if (user != null)
			view.OnChangePasswordSuccess(user);
		else
			view.OnChangePasswordFailure("Failed to change password");
	}
}
