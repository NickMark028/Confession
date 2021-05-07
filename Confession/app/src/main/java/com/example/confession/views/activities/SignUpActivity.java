package com.example.confession.views.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.user_done.SignUpBinder;
import com.example.confession.presenters.user_partially_done.SignUpPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public abstract class SignUpActivity extends AppCompatActivity implements SignUpBinder.View {

	SignUpPresenter presenter;
	private TextInputEditText su_username, su_email, su_phone, su_password, su_confirm_pass;
	private Button su_button;
	private TextView txt_si_click;
	private TextInputLayout til_su_username,til_su_email,
			til_su_phone,til_su_pass,til_su_confirmpass;
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

	private void InitProgressDialog(String msg){
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
				if(!ValidateUsername() | !ValidateEmail() | !ValidatePhone()
				| !ValidatePass() | !ValidateConfirmPass()){
					Toast.makeText(getApplicationContext(), "Sign up failed", Toast.LENGTH_SHORT).show();
					return;
				}

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

	private boolean ValidateUsername(){
		String username = su_username.getText().toString().trim();

		if(username.isEmpty()){
			til_su_username.setError("Field can't be empty");
			return false;
		}

		til_su_username.setError(null);
		return true;
	}

	private boolean ValidateEmail(){
		String email = su_email.getText().toString().trim();

		if(email.isEmpty()){
			til_su_email.setError("Field can't be empty");
			return false;
		}
		til_su_email.setError(null);
		return true;
	}

	private boolean ValidatePhone(){
		String phone = su_phone.getText().toString().trim();

		if(phone.isEmpty()){
			til_su_phone.setError("Field can't be empty");
			return false;
		}
		if(phone.length() != 10){

			til_su_phone.setError("We only use VietNam phone format");
			return false;
		}
		else if(!phone.startsWith("03") && !phone.startsWith("05") &&
				!phone.startsWith("07") && !phone.startsWith("08") &&
				!phone.startsWith("09")){
			til_su_phone.setError("(VN) 03x, 05x, 07x, 08x, 09x");
			return false;
		}

		til_su_phone.setError(null);
		return true;
	}

	private boolean ValidatePass(){
		String pass = su_password.getText().toString();

		if(pass.isEmpty()){
			til_su_pass.setError("Field can't be empty");
			til_su_pass.setErrorIconDrawable(null);
			return false;
		}

		til_su_pass.setError(null);
		return true;
	}

	private boolean ValidateConfirmPass(){
		String pass = su_password.getText().toString();
		String confirm = su_confirm_pass.getText().toString();

		if(confirm.isEmpty()){
			til_su_confirmpass.setError("Field can't be empty");
			til_su_confirmpass.setErrorIconDrawable(null);
			return false;
		}

		if(!pass.equals(confirm)){
			til_su_confirmpass.setError("Password not match");
			til_su_confirmpass.setErrorIconDrawable(null);
			return false;
		}


		til_su_confirmpass.setError(null);
		til_su_confirmpass.setErrorIconDrawable(null);
		return true;
	}



//	@Override
//	public void OnSignUpSuccess(User user) {
//		Toast.makeText(getApplicationContext(), "Sign up success", Toast.LENGTH_LONG).show();
//		Intent myIntent = new Intent(this, HomePageActivity.class);
//		startActivity(myIntent);
//	}

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

		Intent myIntent = new Intent(this, SignInActivity.class);
		startActivity(myIntent);

		finish();
	}

	@Override
	public void OnSignUpFailure(String error) {
		newT.interrupt();
		progressDialog.dismiss();

		// TODO Add error code display
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
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
