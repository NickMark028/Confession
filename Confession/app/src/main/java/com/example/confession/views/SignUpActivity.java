package com.example.confession.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.SupportActionModeWrapper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.SignUpBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.presenters.SignInPresenter;
import com.example.confession.presenters.SignUpPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity implements SignUpBinder.View {

    SignUpPresenter presenter;
    private TextInputEditText su_username, su_password, su_email, su_confirm_pass;
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
        su_password = findViewById(R.id.su_password);
        su_confirm_pass = findViewById(R.id.su_confirm_pass);
        su_button = findViewById(R.id.su_button);

        txt_si_click= findViewById(R.id.txt_si_click);
        //forgot_pass_click= findViewById(R.id.forgot_pass_click);
    }

    private void InitListener() {

        su_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //presenter.HandleLogin(si_username.getText().toString(), si_password.getText().toString());
            }
        });



        txt_si_click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Add sign in", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(myIntent);
            }
        });


    }

    @Override
    public Context GetContext() { return getApplicationContext(); }

    @Override
    public void OnSignUpSuccess(User user) {
        Toast.makeText(getApplicationContext(), "Sign up success", Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(this, HomePageActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void OnSignUpFailure(int error_code) {
        Toast.makeText(getApplicationContext(), "Sign up failure", Toast.LENGTH_LONG).show();
    }
}