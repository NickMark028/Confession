package com.example.confession.views.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.SignInBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.presenters.SignInPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class SignInActivity extends Activity implements SignInBinder.View {

	private SignInBinder.Presenter presenter;

	private TextInputEditText si_username, si_password;
	private Button si_button, fb_button, google_button;
	private TextView txt_su_click, forgot_pass_click;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		InitPresenter();
		InitView();
		InitListener();
	}

	private void InitPresenter() {

		presenter = new SignInPresenter(this);
	}

	private void InitView() {

		si_username = findViewById(R.id.si_username);
		si_password = findViewById(R.id.si_password);
		si_button = findViewById(R.id.si_button);
		fb_button = findViewById(R.id.fb_button);
		google_button = findViewById(R.id.google_button);
		txt_su_click= findViewById(R.id.txt_su_click);
		forgot_pass_click= findViewById(R.id.forgot_pass_click);
	}

	private void InitListener() {

		si_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				presenter.HandleLogin(
						si_username.getText().toString(),
						si_password.getText().toString());
			}
		});

		fb_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "Add forgot password", Toast.LENGTH_LONG).show();
			}
		});

		google_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "Add google sign up", Toast.LENGTH_LONG).show();
			}
		});

		txt_su_click.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				Intent myIntent = new Intent(getApplicationContext(), SignUpActivity.class);
				startActivity(myIntent);
			}
		});

		forgot_pass_click.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "Forgot password", Toast.LENGTH_LONG).show();
				Intent myIntent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
				startActivity(myIntent);
			}
		});
	}

	@SuppressLint("ShowToast")
	@Override
	public void OnLoginSuccess(User user) {

		Intent myIntent = new Intent(this, HomePageActivity.class);
		user.AddTo(myIntent);
		startActivity(myIntent);
	}

	@SuppressLint("ShowToast")
	@Override
	public void OnLoginFailure(int error_code) {
		Toast.makeText(getApplicationContext(), "Login failure", Toast.LENGTH_LONG).show();
	}
}
