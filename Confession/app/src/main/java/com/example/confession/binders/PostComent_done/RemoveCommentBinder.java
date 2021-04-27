package com.example.confession.binders.PostComent_done;

public interface RemoveCommentBinder {
    interface View{
        void OnRemoveCommentSuccess();
        void OnRemoveCommentFail(String error);
    }
    interface Presenter{
        void HandleRemoveCommentSuccess();
    }
}
