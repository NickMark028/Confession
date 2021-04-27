package com.example.confession.binders.comment_done;

public interface RemovePostBinder {
    interface View{
        void OnRemovePostSuccess();
        void OnRemovePostFailure(String error);
    }
    interface Presenter{
        void HandleRemovePost();
    }
}
