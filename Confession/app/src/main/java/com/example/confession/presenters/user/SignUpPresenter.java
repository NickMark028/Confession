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
		String phone = su_phone.getText().toString().trim();

		if (phone.isEmpty()) {
			til_su_phone.setError("Field can't be empty");
			return false;
		}
		if (phone.length() != 10) {

			til_su_phone.setError("We only use VietNam phone format");
			return false;
		} else if (!phone.startsWith("03") && !phone.startsWith("05") &&
				!phone.startsWith("07") && !phone.startsWith("08") &&
				!phone.startsWith("09")) {
			til_su_phone.setError("(VN) 03x, 05x, 07x, 08x, 09x");
			return false;
		}

		til_su_phone.setError(null);
		return true;
	}

	private int ValidatePass(String password) {
		String pass = su_password.getText().toString();

		if (pass.isEmpty()) {
			til_su_pass.setError("Field can't be empty");
			til_su_pass.setErrorIconDrawable(null);
			return false;
		}

		if (!Regex.PASSWORD_PATTERN.matcher(pass).matches()) {
			til_su_pass.setError("Password too weak");
			til_su_pass.setErrorIconDrawable(null);
			return false;
		}

		til_su_pass.setError(null);
		return true;
	}

	private int ValidateConfirmPass(String pass, String confirm_pass) {
		String pass = su_password.getText().toString();
		String confirm = su_confirm_pass.getText().toString();

		if (confirm.isEmpty()) {
			til_su_confirmpass.setError("Field can't be empty");
			til_su_confirmpass.setErrorIconDrawable(null);
			return false;
		}

		if (!pass.equals(confirm)) {
			til_su_confirmpass.setError("Password not match");
			til_su_confirmpass.setErrorIconDrawable(null);
			return false;
		}


		til_su_confirmpass.setError(null);
		til_su_confirmpass.setErrorIconDrawable(null);
		return true;
	}

}
