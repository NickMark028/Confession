package com.example.confession.presenters;

import com.example.confession.binders.CommentBinder;
import com.example.confession.models.behaviors.PostComment;

public class CommentPresenter implements CommentBinder.Presenter {
    CommentBinder.View view;

    @Override
    public void HandlePostComment(PostComment pc) {
        //...

        view.OnPostCommentSuccess(200);
    }
}
