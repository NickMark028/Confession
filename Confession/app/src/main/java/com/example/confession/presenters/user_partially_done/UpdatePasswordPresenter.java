package com.example.confession.presenters.user_partially_done;

import com.example.confession.binders.user.UpdatePasswordBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.utilities.Hashing;

public class UpdatePasswordPresenter implements UpdatePasswordBinder.Presenter {

	private final UpdatePasswordBinder.View view;

	public UpdatePasswordPresenter(UpdatePasswordBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleUpdatePassword(String old_pass, String new_pass) {

		if (old_pass == new_pass) {
			view.OnUpdatePasswordFailure("New password can't be the same as the old one");
			return;
		}

		old_pass = Hashing.SHA512(old_pass);
		new_pass = Hashing.SHA512(new_pass);
		User user = User.GetInstance().UpdatePassword(old_pass, new_pass);

		if (user != null)
			view.OnUpdatePasswordSuccess(user);
		else
			view.OnUpdatePasswordFailure("Failed to change password");
	}
}
