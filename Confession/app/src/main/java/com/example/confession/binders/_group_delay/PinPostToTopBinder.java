package com.example.confession.binders._group_delay;

public interface PinPostToTopBinder {
    interface View{
        void OnPinPostToTopSuccess();
        void OnPinPostToTopFailure(String error);
    }
    interface Presenter{
        void HandlePinPostToTop();
    }
}
