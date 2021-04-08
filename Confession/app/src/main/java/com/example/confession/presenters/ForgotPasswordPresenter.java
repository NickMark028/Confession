package com.example.confession.presenters;

import com.example.confession.binders.ForgotPasswordBinder;
import com.example.confession.models.behaviors.User;

public class ForgotPasswordPresenter implements ForgotPasswordBinder.Presenter {

    private ForgotPasswordBinder.View view;
    private User user;

    public ForgotPasswordPresenter(ForgotPasswordBinder.View view){
        this.view = view;
    }

    @Override
    public void HandleSendEmailToResetPassword(String email) {
        //Do action here
        view.OnResetPasswordSuccess(user);
    }
}
