package com.example.confession.presenters.user_partially_done;

import com.example.confession.binders.user.ChangeUserInfoBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.UserInfo;

public class ChangeUserInfoPresenter implements ChangeUserInfoBinder.Presenter {

	private final ChangeUserInfoBinder.View view;

	public ChangeUserInfoPresenter(ChangeUserInfoBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleChangeUserInfo(String fullname, String email, String phone) {

		User user = User.GetInstance();

		BasicUserInfo basic_info = new BasicUserInfo(user.GetBasicUserInfo().username, fullname);
		UserInfo info = new UserInfo(basic_info, email, phone);
		user = user.UpdateUserInfo(info);

		if (user != null)
			view.OnChangeUserInfoSuccess(user);
		else
			view.OnChangeUserInfoFailure("Failed to change password");
	}
}
