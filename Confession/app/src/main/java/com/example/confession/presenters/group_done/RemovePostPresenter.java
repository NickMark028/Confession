package com.example.confession.presenters.group_done;

import com.example.confession.binders.PostComent_done.RemoveCommentBinder;

public class RemovePostPresenter implements RemoveCommentBinder.Presenter {
    private final RemoveCommentBinder.View view;

    public RemovePostPresenter(RemoveCommentBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandleRemoveCommentSuccess() {
        if(true){
            view.OnRemoveCommentSuccess();
        }
        else {
            view.OnRemoveCommentFailure("Failed to Remove Post");
        }
    }
}
