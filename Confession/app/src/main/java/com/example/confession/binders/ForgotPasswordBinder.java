package com.example.confession.binders;

import android.content.Context;

import com.example.confession.models.behaviors.User;

public interface ForgotPasswordBinder {

    interface View {

        void OnResetPasswordSuccess(User user);
        void OnResetPasswordFailure(int error_code);
    }

    interface Presenter {

        void HandleSendEmailToResetPassword(String email);
    }
}
