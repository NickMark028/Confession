package com.example.confession.binders.comment_done;

public interface AcceptPostBinder {
    interface View{
        void OnAcceptPostSuccess();;
        void OnAcceptPostFailure(String error);
    }
    interface Presenter{
        void HandleAcceptPost();
    }
}
