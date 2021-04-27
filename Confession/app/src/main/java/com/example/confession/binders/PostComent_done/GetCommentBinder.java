package com.example.confession.binders.PostComent_done;

import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;

import java.util.ArrayList;

public interface GetCommentBinder {
    interface View{
        void OnGetCommentSuccess(ArrayList<PostCommentInfo> Comment);
        void OnGetCommentFailure(String error);
    }
    interface Presenter{
        void HandleGetComment(PostCommentInfo comment_info, GroupPostInfo post);
    }
}
