package com.example.confession.presenters;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.widget.ImageView;

import com.example.confession.binders.SignUpBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.UserInfo;
import com.example.confession.utilities.Hashing;

public class SignUpPresenter implements SignUpBinder.Presenter {

	private final SignUpBinder.View view;

	public SignUpPresenter(SignUpBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleSignUp(String name, String username, String email, String phone, String password, String confirm_pass, Object avatar) {

		if (!password.equals(confirm_pass)) {
			view.OnSignUpFailure("Password doesn't match");
			return;
		}

		String hash_pass = Hashing.SHA512(password);
		BasicUserInfo basic_user_info = new BasicUserInfo(username, "Add a name here", avatar);
		UserInfo user_info = new UserInfo(basic_user_info, email, phone);

		if (User.Register(user_info, hash_pass))
			view.OnSignUpSuccess();
		else
			view.OnSignUpFailure("Failed to create account");
	}
}
