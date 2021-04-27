package com.example.confession.presenters.postComment_done;

import com.example.confession.binders.PostComent_done.RemoveCommentBinder;

public class RemoveCommentPresenter implements RemoveCommentBinder.Presenter {
    private final RemoveCommentBinder.View view;

    public RemoveCommentPresenter(RemoveCommentBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandleRemoveCommentSuccess() {
        if(true){
            view.OnRemoveCommentSuccess();
        }
        else
        {
            view.OnRemoveCommentFailure("False");
        }
    }
}
