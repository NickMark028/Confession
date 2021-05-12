package com.example.confession.binders.group_delay;

public interface RemovePostBinder {
    interface View{
        void OnRemovePostSuccess();
        void OnRemovePostFailure(String error);
    }
    interface Presenter{
        void HandleRemovePost();
    }
}
