package com.example.confession.views.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.user.SignInBinder;
import com.example.confession.binders.user.SignUpBinder;
import com.example.confession.presenters.user.SignUpPresenter;
import com.example.confession.utilities.Regex;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity implements SignUpBinder.View {

	SignUpPresenter presenter;
	private TextInputEditText su_username, su_email, su_phone, su_password, su_confirm_pass;
	private Button su_button;
	private TextView txt_si_click;
	private TextInputLayout til_su_username, til_su_email,
			til_su_phone, til_su_pass, til_su_confirmpass;
	private AlertDialog.Builder builder;
	private AlertDialog progressDialog;
	private Thread newT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_sign_up);

		InitPresenter();
		InitView();
		InitListener();
	}

	private void InitPresenter() {
		presenter = new SignUpPresenter(this);
	}

	private void InitView() {

		til_su_username = findViewById(R.id.til_su_username);
		til_su_email = findViewById(R.id.til_su_email);
		til_su_phone = findViewById(R.id.til_su_phone);
		til_su_pass = findViewById(R.id.til_su_pass);
		til_su_confirmpass = findViewById(R.id.til_su_confirmpass);

		su_username = findViewById(R.id.su_username);
		su_email = findViewById(R.id.su_email);
		su_phone = findViewById(R.id.su_phone);
		su_password = findViewById(R.id.su_password);
		su_confirm_pass = findViewById(R.id.su_confirm_pass);
		su_button = findViewById(R.id.su_button);

		su_phone.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
		su_phone.setTransformationMethod(new NumericKeyBoardTransformationMethod());
		txt_si_click = findViewById(R.id.txt_si_click);
		//forgot_pass_click= findViewById(R.id.forgot_pass_click);
	}

	private void InitProgressDialog(String msg) {

		builder = new AlertDialog.Builder(SignUpActivity.this);
		builder.setCancelable(false);

		progressDialog = builder.create();
		View view = getLayoutInflater().inflate(R.layout.progress_dialog_layout, null);
		TextView txt_dialog_progress = view.findViewById(R.id.txt_dialog_progress);
		txt_dialog_progress.setText(msg);

		progressDialog.setView(view);
		progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		progressDialog.show();
	}

	private void InitListener() {

		su_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//After GUI check
				newT = new Thread(new Runnable() {
					@Override
					public void run() {
						presenter.HandleSignUp(
								String.format("unknown+" + su_username.getText().toString()),
								su_username.getText().toString(),
								su_email.getText().toString(),
								su_phone.getText().toString(),
								su_password.getText().toString(),
								su_confirm_pass.getText().toString(),
								"Add avatar here");
					}
				});
				newT.start();

				//Init Dialog
				InitProgressDialog("Please wait...");
			}
		});

		txt_si_click.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
//				Toast.makeText(getApplicationContext(), "Add sign in", Toast.LENGTH_LONG).show();
//				Intent myIntent = new Intent(getApplicationContext(), SignInActivity.class);
//				startActivity(myIntent);
				finish();
			}
		});
	}

	@Override
	public void OnSignUpSuccess() {
		newT.interrupt();
		progressDialog.dismiss();

		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), "Account registered", Toast.LENGTH_LONG).show();
			}
		});

		finish();
	}

	@Override
	public void OnSignUpFailure(int error_code) {
		newT.interrupt();

		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {

				progressDialog.dismiss();

				// USERNAME
				if ((error_code & SignUpBinder.ERROR_EMPTY_USERNAME) != 0) {
					til_su_username.setError("Field can't be empty");
					til_su_username.setErrorIconDrawable(null);
				}
				else if ((error_code & SignUpBinder.ERROR_INVALID_USERNAME) != 0) {
					til_su_username.setError("Please use a valid username");
					til_su_username.setErrorIconDrawable(null);
				}
				else til_su_username.setError(null);

				// EMAIL
				if ((error_code & SignUpBinder.ERROR_EMPTY_EMAIL) != 0) {
					til_su_email.setError("Field can't be empty");
					til_su_email.setErrorIconDrawable(null);
				}
				else if ((error_code & SignUpBinder.ERROR_INVALID_EMAIL) != 0) {
					til_su_email.setError("Please enter a valid email");
					til_su_email.setErrorIconDrawable(null);
				}
				else til_su_email.setError(null);

				// PHONE
				if ((error_code & SignUpBinder.ERROR_EMPTY_PHONE) != 0) {
					til_su_phone.setError("Field can't be empty");
					til_su_phone.setErrorIconDrawable(null);
				}
				else if ((error_code & SignUpBinder.ERROR_NON_VN_FORMAT) != 0) {
					til_su_phone.setError("We only use Viet Nam phone format");
					til_su_phone.setErrorIconDrawable(null);
				}
				else if ((error_code & SignUpBinder.ERROR_PHONE_FORMAT) != 0) {
					til_su_phone.setError("(VN) 03x, 05x, 07x, 08x, 09x");
					til_su_phone.setErrorIconDrawable(null);
				}
				else til_su_phone.setError(null);

				// PASS
				if ((error_code & SignUpBinder.ERROR_EMPTY_PASS) != 0) {
					til_su_pass.setError("Field can't be empty");
					til_su_pass.setErrorIconDrawable(null);
				}
				else if ((error_code & SignUpBinder.ERROR_WEAK_PASS) != 0) {
					til_su_pass.setError("Password too weak");
					til_su_pass.setErrorIconDrawable(null);
				}
				else til_su_pass.setError(null);

				// CONFIRM PASS
				if ((error_code & SignUpBinder.ERROR_EMPTY_CONFIRM) != 0) {
					til_su_confirmpass.setError("Field can't be empty");
					til_su_confirmpass.setErrorIconDrawable(null);
				}
				else if ((error_code & SignUpBinder.ERROR_MISMATCH_PASS) != 0) {
					til_su_confirmpass.setError("Password not match");
					til_su_confirmpass.setErrorIconDrawable(null);
				}
				else {
					til_su_confirmpass.setError(null);
					til_su_confirmpass.setErrorIconDrawable(null);
				}

				// OTHERWISE
				Toast.makeText(getApplicationContext(), "Sign up failed", Toast.LENGTH_LONG).show();
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
