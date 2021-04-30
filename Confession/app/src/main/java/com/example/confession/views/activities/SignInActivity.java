package com.example.confession.views.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.confession.R;
import com.example.confession.binders.user.SignInBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.presenters.user.SignInPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class SignInActivity extends Activity implements SignInBinder.View {

	private SignInBinder.Presenter presenter;

	private TextInputEditText si_username, si_password;
	private Button si_button, fb_button, google_button;
	private TextView txt_su_click, forgot_pass_click;
	private AlertDialog.Builder builder;
	private AlertDialog progressDialog;
	private Thread newT;

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
		txt_su_click = findViewById(R.id.txt_su_click);
		forgot_pass_click = findViewById(R.id.forgot_pass_click);

		// Todo Debug
		si_username.setText("admin");
		si_password.setText("1");
	}

	private void InitProgressDialog(String msg) {
		builder = new AlertDialog.Builder(SignInActivity.this);
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

		si_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				newT = new Thread(new Runnable() {
					@Override
					public void run() {
						presenter.HandleLogin(
								si_username.getText().toString(),
								si_password.getText().toString());
					}
				});

				newT.start();

				//Init Dialog
				InitProgressDialog("Logging in");
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

		txt_su_click.setOnClickListener(view -> {
			Intent myIntent = new Intent(SignInActivity.this, SignUpActivity.class);
			startActivity(myIntent);
		});

		forgot_pass_click.setOnClickListener(view -> {
			Toast.makeText(getApplicationContext(), "Forgot password", Toast.LENGTH_LONG).show();
			Intent myIntent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
			startActivity(myIntent);
		});
	}

	@SuppressLint("ShowToast")
	@Override
	public void OnLoginSuccess(User user) {
		newT.interrupt();
		progressDialog.dismiss();
		Intent myIntent = new Intent(this, HomePageActivity.class);
		startActivity(myIntent);
		finish();
	}

	@SuppressLint("ShowToast")
	@Override
	public void OnLoginFailure(String error) {
		newT.interrupt();
		progressDialog.dismiss();
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), "Login failure", Toast.LENGTH_LONG).show();
			}
		});


	}
}
