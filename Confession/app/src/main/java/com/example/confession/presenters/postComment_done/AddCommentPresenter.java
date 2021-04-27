package com.example.confession.presenters.postComment_done;


import com.example.confession.binders.PostComent_done.AddCommentBinder;
import com.example.confession.models.data.PostCommentInfo;

public class AddCommentPresenter implements AddCommentBinder.Presenter {

    @Override
    public void HandleAddComment(PostCommentInfo comment) {
        PostCommentInfo cmt = new PostCommentInfo(comment);


    }
}
