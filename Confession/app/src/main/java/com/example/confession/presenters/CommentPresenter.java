package com.example.confession.presenters;

import com.example.confession.binders.CommentBinder;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.PostCommentInfo;

public class CommentPresenter implements CommentBinder.Presenter {

    CommentBinder.View view;

    public CommentPresenter(CommentBinder.View view) {

        this.view = view;
    }

//    @Override
//    public void HandlePostComment(GroupPost post, String content) {
//
////        PostCommentInfo info = new PostCommentInfo();
////        User user = User.GetInstance();
////        post.AddComment(info, User.GetInstance());

//    }

    @Override
    public void HandlePostComment(String content) {

    }
}
