package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.SignUpBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.presenters.SignUpPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity implements SignUpBinder.View {

	SignUpPresenter presenter;
	private TextInputEditText su_username, su_email, su_phone, su_password, su_confirm_pass;
	private Button su_button;
	private TextView txt_si_click;

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

		su_username = findViewById(R.id.su_username);
		su_email = findViewById(R.id.su_email);
		su_phone = findViewById(R.id.su_phone);
		su_password = findViewById(R.id.su_password);
		su_confirm_pass = findViewById(R.id.su_confirm_pass);
		su_button = findViewById(R.id.su_button);

		txt_si_click = findViewById(R.id.txt_si_click);
		//forgot_pass_click= findViewById(R.id.forgot_pass_click);
	}

	private void InitListener() {

		su_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				presenter.HandleSignUp(
						"Add name here",
						su_username.getText().toString(),
						su_email.getText().toString(),
						su_phone.getText().toString(),
						su_password.getText().toString(),
						su_confirm_pass.getText().toString());
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

//	@Override
//	public void OnSignUpSuccess(User user) {
//		Toast.makeText(getApplicationContext(), "Sign up success", Toast.LENGTH_LONG).show();
//		Intent myIntent = new Intent(this, HomePageActivity.class);
//		startActivity(myIntent);
//	}

	@Override
	public void OnSignUpSuccess() {
		Toast.makeText(getApplicationContext(), "Account registered", Toast.LENGTH_LONG).show();
		finish();
	}

	@Override
	public void OnSignUpFailure(int error_code) {

		// TODO Add error code display
		Toast.makeText(getApplicationContext(), "Sign up failed", Toast.LENGTH_LONG).show();
	}
}
