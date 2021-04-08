package com.example.confession.binders;

import android.content.Context;

import com.example.confession.models.behaviors.User;

public interface ForgotPasswordBinder {
    interface View {

        Context GetContext();

        void OnResetPasswordSuccess(User user);
        void OnResetPasswordFailure(int error_code);
    }

    interface Presenter {

        void HandleSendEmailToResetPassword(String email);
    }
}
