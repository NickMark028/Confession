package com.example.confession.binders.user;

import android.app.Activity;

public interface SignOutBinder {

    interface View {

        void OnSignOutSuccess();

        void OnSignOutFailure(String error);
    }
}
