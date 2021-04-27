package com.example.confession.binders.comment_done;

public interface RejectPostBinder {
    interface View{
        void OnRejectPostSuccess();
        void OnRejectPostFailure(String error);
    }
    interface Presenter{
        void HandleRejectPost();
    }
}
