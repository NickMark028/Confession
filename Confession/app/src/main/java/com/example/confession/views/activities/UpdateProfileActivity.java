package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.user.ChangeUserInfoBinder;
import com.example.confession.binders.user.UpdatePasswordBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.presenters.user.ChangeUserInfoPresenter;
import com.example.confession.presenters.user.UpdatePasswordPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class UpdateProfileActivity extends AppCompatActivity implements ChangeUserInfoBinder.View {

	private ChangeUserInfoBinder.Presenter presenter;
	private TextInputEditText up_fullname, up_email, up_phone;
	private TextInputLayout til_up_fullname, til_up_email, til_up_phone;
	private ImageButton up_close_btn, up_confirm;
	private ProgressBar up_loading;
	private Thread newThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_profile);

		InitPresenter();
		InitView();
		InitListener();
	}

	private void InitPresenter() {
		presenter = new ChangeUserInfoPresenter(this);
	}

	private void InitView() {
		// up - update profile

		up_fullname = findViewById(R.id.up_fullname);
		up_email = findViewById(R.id.up_email);

		up_phone = findViewById(R.id.up_phone);
		up_phone.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
		up_phone.setTransformationMethod(new NumericKeyBoardTransformationMethod());

		til_up_fullname = findViewById(R.id.til_up_fullname);
		til_up_email = findViewById(R.id.til_up_email);
		til_up_phone = findViewById(R.id.til_up_phone);

		up_close_btn = findViewById(R.id.up_close_btn);
		up_confirm = findViewById(R.id.up_confirm);

		up_loading = findViewById(R.id.up_loading);
	}

	private void InitListener() {
		up_close_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		up_confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (!ValidateFullname() | !ValidateEmail() | !ValidatePhone()) {
					Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
					return;
				}

				up_confirm.setVisibility(View.GONE);
				up_loading.setVisibility(View.VISIBLE);

				UpdateProfile(
						up_fullname.getText().toString(),
						up_email.getText().toString(),
						up_phone.getText().toString()
				             );
			}
		});
	}

	//Call presenter to update profile here
	private void UpdateProfile(String fname, String email, String phone) {
		newThread = new Thread(new Runnable() {
			@Override
			public void run() {
				presenter.HandleChangeUserInfo(fname, email, phone);
			}
		});
		newThread.start();
	}

	//Validate data
	private boolean ValidateFullname() {
		String username = up_fullname.getText().toString();

		if (username.isEmpty()) {
			til_up_fullname.setError("Field can't be empty");
			return false;
		}

		til_up_fullname.setError(null);
		return true;
	}

	private boolean ValidateEmail() {
		String email = up_email.getText().toString().trim();

		if (email.isEmpty()) {
			til_up_email.setError("Field can't be empty");
			return false;
		}

		if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
			til_up_email.setError("Please enter a valid email");
			return false;
		}
		til_up_email.setError(null);
		return true;
	}

	private boolean ValidatePhone() {
		String phone = up_phone.getText().toString().trim();

		if (phone.isEmpty()) {
			til_up_phone.setError("Field can't be empty");
			return false;
		}
		if (phone.length() != 10) {

			til_up_phone.setError("We only use VietNam phone format");
			return false;
		}
		else if (!phone.startsWith("03") && !phone.startsWith("05") &&
				!phone.startsWith("07") && !phone.startsWith("08") &&
				!phone.startsWith("09")) {
			til_up_phone.setError("(VN) 03x, 05x, 07x, 08x, 09x");
			return false;
		}

		til_up_phone.setError(null);
		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (newThread != null && newThread.isAlive()) {
			newThread.interrupt();
		}
	}

	@Override
	public void OnChangeUserInfoSuccess(User user) {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}

	@Override
	public void OnChangeUserInfoFailure(String error) {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				up_loading.setVisibility(View.GONE);
				up_confirm.setVisibility(View.VISIBLE);

				Toast.makeText(getApplicationContext(), "Update Info Failed", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
		@Override
		public CharSequence getTransformation(CharSequence source, View view) {
			return source;
		}
	}
}