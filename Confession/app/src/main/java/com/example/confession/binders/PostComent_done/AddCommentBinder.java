package com.example.confession.binders.PostComent_done;

import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.PostCommentInfo;


import java.util.ArrayList;

public interface AddCommentBinder {
    interface View {

        void OnAddCommentSuccess(PostComment Comment);
        void OnAddCommentFailure(String error);
    }

    interface Presenter {
        void HandleAddComment(PostCommentInfo comment);
    }
}

