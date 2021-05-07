package com.example.confession.binders.user;

public interface SignUpBinder {

    interface View {

        void OnSignUpSuccess();
        void OnSignUpFailure(String error);
    }

    interface Presenter {

        // Todo: Add avatar here
        void HandleSignUp(String name, String username, String email, String phone, String password, String confirm_pass, Object avatar);
    }
}
