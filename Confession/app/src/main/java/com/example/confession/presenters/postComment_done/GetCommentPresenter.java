package com.example.confession.presenters.postComment_done;

import com.example.confession.binders.PostComent_done.GetCommentBinder;
import com.example.confession.binders.comment_done.GetPostsBinder;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;

import java.util.ArrayList;

public class GetCommentPresenter implements GetCommentBinder.Presenter {
    private final GetCommentBinder.View view;

    public GetCommentPresenter(GetCommentBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandleGetComment(PostCommentInfo comment_info, GroupPostInfo post) {
        PostComment cmt = new PostComment(comment_info,post);
        ArrayList<PostCommentInfo> comments = cmt.GetComments(PostComment.GetAuthToken());
        if(comments !=null){
            view.OnGetCommentSuccess(comments);
        }
        else{
            view.OnGetCommentFailure("Failed to get comment!");
        }

    }
}
