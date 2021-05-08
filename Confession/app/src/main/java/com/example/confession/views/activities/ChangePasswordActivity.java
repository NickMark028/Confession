package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.user.UpdatePasswordBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.presenters.user.UpdatePasswordPresenter;
import com.example.confession.utilities.Regex;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordActivity extends AppCompatActivity implements UpdatePasswordBinder.View {

    private UpdatePasswordBinder.Presenter presenter;
    private TextInputEditText change_pass_curr_pass, change_pass_new_pass, change_pass_confirm_pass;
    private TextInputLayout til_cp_curr_pass, til_cp_new_pass, til_cp_confirm_pass;
    private ImageButton change_pass_close_btn, change_pass_confirm;
    private ProgressBar change_pass_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        InitPresenter();
        InitView();
        InitListener();

    }

    private void InitPresenter(){presenter = new UpdatePasswordPresenter(this);}

    private void InitView(){
        change_pass_curr_pass = findViewById(R.id.change_pass_curr_pass);
        change_pass_new_pass = findViewById(R.id.change_pass_new_pass);
        change_pass_confirm_pass = findViewById(R.id.change_pass_confirm_pass);

        til_cp_curr_pass = findViewById(R.id.til_cp_curr_pass);
        til_cp_new_pass = findViewById(R.id.til_cp_new_pass);
        til_cp_confirm_pass = findViewById(R.id.til_cp_confirm_pass);

        change_pass_close_btn = findViewById(R.id.change_pass_close_btn);
        change_pass_confirm = findViewById(R.id.change_pass_confirm);

        change_pass_loading = findViewById(R.id.change_pass_loading);
    }

    private void InitListener(){
        change_pass_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        change_pass_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ValidateNewPassword() | !ValidateCurrentPassword() | !ValidateConfirmPassword()){
                    Toast.makeText(getApplicationContext(), "Update failed", Toast.LENGTH_SHORT).show();
                    return;
                }

                change_pass_confirm.setVisibility(View.GONE);
                change_pass_loading.setVisibility(View.VISIBLE);

                UpdatePassword(
                        change_pass_curr_pass.getText().toString(),
                        change_pass_new_pass.getText().toString()
                );
            }
        });
    }

    //Call presenter to update password here
    private void UpdatePassword(String currpass, String newpass){
        new Thread(new Runnable() {
            @Override
            public void run() {
                presenter.HandleUpdatePassword(currpass, newpass);
            }
        }).start();
    }

    //Validate Password
    private boolean ValidateCurrentPassword(){
        String password = change_pass_curr_pass.getText().toString();

        if(password.isEmpty()){
            til_cp_curr_pass.setError("Field can't be empty");
            til_cp_curr_pass.setErrorIconDrawable(null);
            return false;
        }



        til_cp_curr_pass.setError(null);
        til_cp_curr_pass.setErrorIconDrawable(null);
        return true;
    }

    private boolean ValidateNewPassword(){
        String password = change_pass_curr_pass.getText().toString();

        if(password.isEmpty()){
            til_cp_new_pass.setError("Field can't be empty");
            til_cp_new_pass.setErrorIconDrawable(null);
            return false;
        }


        if(!Regex.PASSWORD_PATTERN.matcher(password).matches()){
            til_cp_new_pass.setError("Password too weak");
            til_cp_new_pass.setErrorIconDrawable(null);
            return false;
        }

        til_cp_new_pass.setError(null);
        til_cp_new_pass.setErrorIconDrawable(null);
        return true;
    }

    private boolean ValidateConfirmPassword(){
        String password = change_pass_confirm_pass.getText().toString();
        String new_pass = change_pass_new_pass.getText().toString();

        if(password.isEmpty()){
            til_cp_confirm_pass.setError("Field can't be empty");
            return false;
        }

        if(password.length() < 6){
            til_cp_confirm_pass.setError("Password must have at least 6 characters");
            til_cp_confirm_pass.setErrorIconDrawable(null);
            return false;
        }

        if(!new_pass.equals(password)){
            til_cp_confirm_pass.setError("Password not match");
            til_cp_confirm_pass.setErrorIconDrawable(null);
            return false;
        }

        til_cp_confirm_pass.setError(null);
        til_cp_confirm_pass.setErrorIconDrawable(null);

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void OnUpdatePasswordSuccess(User user) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Update successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void OnUpdatePasswordFailure(String error) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                change_pass_loading.setVisibility(View.GONE);
                change_pass_confirm.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Update password failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}