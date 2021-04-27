package com.example.confession.binders.comment_done;

public interface PinPostToTopBinder {
    interface View{
        void OnPinPostToTopSuccess();
        void OnPinPostToTopFailure(String error);
    }
    interface Presenter{
        void HandlePinPostToTop();
    }
}
