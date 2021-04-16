package com.example.confession.binders;

import android.content.Context;

import com.example.confession.models.behaviors.User;

public interface SignUpBinder {

    interface View {

        void OnSignUpSuccess();

        void OnSignUpFailure(int error_code);
    }

    interface Presenter {

        // Todo: Add avatar here
        void HandleSignUp(String name, String username, String email, String phone, String password, String confirm_pass);
    }
}
