package com.example.confession.binders;

import android.content.Context;

import com.example.confession.models.behaviors.User;

public interface SignUpBinder {
    interface View {

        Context GetContext();

        void OnSignUpSuccess(User user);
        void OnSignUpFailure(int error_code);
    }

    interface Presenter {

        void HandleSignUp(String username, String email, String password, String confirm_pass);
    }
}
