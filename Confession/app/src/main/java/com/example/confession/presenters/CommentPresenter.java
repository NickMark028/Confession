package com.example.confession.presenters;

import com.example.confession.binders.CommentBinder;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.PostCommentInfo;

public class CommentPresenter implements CommentBinder.Presenter {

    CommentBinder.View view;

    public CommentPresenter(CommentBinder.View view) {

        this.view = view;
    }

    @Override
    public void HandlePostComment(GroupPost post, BasicUserInfo user, String content) {

        PostCommentInfo info = new PostCommentInfo(user, content);
        PostComment comment = post.AddComment(info, user);

        if (comment != null)
            view.OnPostCommentSuccess();
        else
            view.OnPostCommentFailure("Failed to add comment");
    }
}
