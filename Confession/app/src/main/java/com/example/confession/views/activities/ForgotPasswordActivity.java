package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.ForgotPasswordBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.presenters.ForgotPasswordPresenter;
import com.example.confession.views.fragments.ForgotPasswordSuccessFragment;
import com.google.android.material.textfield.TextInputEditText;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordBinder.View{
    ForgotPasswordPresenter presenter;
    private TextInputEditText fp_email;
    private Button fp_button;
    private TextView txt_si_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        InitPresenter();
        InitView();
        InitListener();
    }

    private void InitPresenter() {
        presenter = new ForgotPasswordPresenter(this);
    }

    private void InitView() {


        fp_email = findViewById(R.id.fp_email);
        fp_button = findViewById(R.id.fp_button);
        txt_si_click= findViewById(R.id.txt_si_click);
        //forgot_pass_click= findViewById(R.id.forgot_pass_click);
    }

    private void InitListener() {

        fp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //presenter.HandleLogin(si_username.getText().toString(), si_password.getText().toString());
                String email = fp_email.getText().toString();

                if(email.contains("@")){
                    presenter.HandleSendEmailToResetPassword(email);
                }

                //Change Fragment
                Fragment sentEmail = new ForgotPasswordSuccessFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_forgot_pass, sentEmail)
                        .commit();
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
    public Context GetContext() {
        return getApplicationContext();
    }

    @Override
    public void OnResetPasswordSuccess(User user) {
        //Notify success message

    }

    @Override
    public void OnResetPasswordFailure(int error_code) {

    }
}