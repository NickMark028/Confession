package com.example.confession.presenters;

import com.example.confession.binders.SignUpBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.UserInfo;

public class SignUpPresenter implements SignUpBinder.Presenter {
    private final SignUpBinder.View view;
    private User user;

    public SignUpPresenter(SignUpBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandleSignUp(String username, String email, String password, String confirm_pass) {

        //user = User.Register
    }
}
