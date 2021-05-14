package com.example.confession.presenters.user;

import android.util.Patterns;

import com.example.confession.binders.user.SignUpBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.UserInfo;
import com.example.confession.utilities.Hashing;
import com.example.confession.utilities.Regex;

public class SignUpPresenter implements SignUpBinder.Presenter {

	private final SignUpBinder.View view;

	public SignUpPresenter(SignUpBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleSignUp(String name, String username, String email, String phone, String password, String confirm_pass, Object avatar) {

		int error_code =
				ValidateUsername(username) | ValidateEmail(email) | ValidatePhone(phone) |
						ValidatePass(password) | ValidateConfirmPass(password, confirm_pass);

		if (error_code != 0) {
			view.OnSignUpFailure(error_code);
			return;
		}

		BasicUserInfo basic_user_info = new BasicUserInfo(username, name, avatar);
		UserInfo user_info = new UserInfo(basic_user_info, email, phone);

		if (User.Register(user_info, Hashing.SHA512(password)))
			view.OnSignUpSuccess();
		else
			view.OnSignUpFailure(SignUpBinder.ERROR_FAILED);
	}

	private int ValidateUsername(String username) {

		int error_code = 0;
		if (username.isEmpty())
			error_code |= SignUpBinder.ERROR_EMPTY_USERNAME;

		if (!Regex.USERNAME_PATTERN.matcher(username).matches())
			error_code |= SignUpBinder.ERROR_INVALID_USERNAME;

		return error_code;
	}

	private int ValidateEmail(String email) {

		int error_code = 0;

		if (email.isEmpty())
			error_code |= SignUpBinder.ERROR_EMPTY_EMAIL;

		if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
			error_code |= SignUpBinder.ERROR_INVALID_EMAIL;

		return error_code;
	}

	private int ValidatePhone(String phone) {

		int error_code = 0;

		if (phone.isEmpty())
			error_code |= SignUpBinder.ERROR_EMPTY_PHONE;

		if (phone.length() != 10)
			error_code |= SignUpBinder.ERROR_NON_VN_FORMAT;

		if (!phone.startsWith("03") && !phone.startsWith("05") &&
				!phone.startsWith("07") && !phone.startsWith("08") &&
				!phone.startsWith("09"))
			error_code |= SignUpBinder.ERROR_PHONE_FORMAT;

		return error_code;
	}

	private int ValidatePass(String password) {

		int error_code = 0;

		if (password.isEmpty())
			error_code |= SignUpBinder.ERROR_EMPTY_PASS;

		if (!Regex.PASSWORD_PATTERN.matcher(password).matches())
			error_code |= SignUpBinder.ERROR_WEAK_PASS;

		return error_code;
	}

	private int ValidateConfirmPass(String pass, String confirm_pass) {

		int error_code = 0;

		if (confirm_pass.isEmpty())
			error_code |= SignUpBinder.ERROR_EMPTY_CONFIRM;

		if (!confirm_pass.equals(pass))
			error_code |= SignUpBinder.ERROR_MISMATCH_PASS;

		return error_code;
	}
}
