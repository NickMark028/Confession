package com.example.confession.binders.user;

public interface SignUpBinder {

    interface View {

        void OnSignUpSuccess();
        void OnSignUpFailure(String error);
    }


}
