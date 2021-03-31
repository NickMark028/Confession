package com.example.confession.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.SignInBinder;
import com.example.confession.presenters.SignInPresenter;

public class SignInActivity extends Activity implements SignInBinder.View {

	private SignInBinder.Presenter presenter;

	private EditText txt_username, txt_password;
	private Button btn_sign_in;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		InitPresenter();
		InitView();
		InitListener();
	}

	private void InitPresenter() {
		presenter = new SignInPresenter(this,getApplicationContext());
	}

	private void InitView() {

		txt_username = findViewById(R.id.txt_username);
		txt_password = findViewById(R.id.txt_password);
		btn_sign_in = findViewById(R.id.btn_login);
	}

	private void InitListener() {

		btn_sign_in.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				presenter.HandleLogin(txt_username.getText().toString(), txt_password.getText().toString());
			}
		});
	}

	@SuppressLint("ShowToast")
	@Override
	public void LoginSuccessfully() {
		Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_LONG).show();
	}

	@SuppressLint("ShowToast")
	@Override
	public void LoginFailure() {
		Toast.makeText(getApplicationContext(), "Login failure", Toast.LENGTH_LONG).show();
	}
}
