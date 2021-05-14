package com.example.confession.binders.user;

public interface SignUpBinder {

    int ERROR_EMPTY_USERNAME = 1 << 0;
    int ERROR_INVALID_USERNAME = 1 << 1;

    int ERROR_EMPTY_EMAIL = 1 << 2;
    int ERROR_INVALID_EMAIL = 1 << 3;

    int ERROR_EMPTY_PHONE = 0b01 << 4;
    int ERROR_NON_VN_FORMAT = 0b10 << 4;
    int ERROR_PHONE_FORMAT = 0b11 << 4;

    int ERROR_EMPTY_PASS = 1 << 6;
    int ERROR_WEAK_PASS = 1 << 7;

    int ERROR_EMPTY_CONFIRM = 1 << 8;
    int ERROR_MISMATCH_PASS = 1 << 9;

    interface View {

        void OnSignUpSuccess();
        void OnSignUpFailure(String error);
    }

    interface Presenter {

        // Todo: Add avatar here
        void HandleSignUp(String name, String username, String email, String phone, String password, String confirm_pass, Object avatar);
    }
}
