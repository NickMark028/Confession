package com.example.confession.presenters.postComment_done;


import com.example.confession.binders.PostComent_done.AddCommentBinder;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;

public class AddCommentPresenter implements AddCommentBinder.Presenter {
    private final AddCommentBinder.View view;

    public AddCommentPresenter(AddCommentBinder.View view) {
        this.view = view;
    }


    @Override
    public void HandleAddComment(PostCommentInfo comment_info, GroupPostInfo post) {
        PostComment cmt = new PostComment(comment_info,post);
        PostComment comments = cmt.AddComment(comment_info,PostComment.GetAuthToken());
        if(comments != null){
            view.OnAddCommentSuccess(comments);
        }
        else {
        view.OnAddCommentFailure("Failed to Add Comment!");
    }}
}
