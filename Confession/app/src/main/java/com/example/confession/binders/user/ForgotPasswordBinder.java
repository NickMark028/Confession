package com.example.confession.binders.user;

import android.content.Context;

import com.example.confession.models.behaviors.User;

public interface ForgotPasswordBinder {

    interface View {

        void OnResetPasswordSuccess(User user);
        void OnResetPasswordFailure(String error);
    }

    interface Presenter {

        void HandleSendEmailToResetPassword(String email);
    }
}
