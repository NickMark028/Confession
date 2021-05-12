package com.example.confession.binders.post;

import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;

import java.util.ArrayList;

public interface GetReactionCountBinder {
    interface View {

        void OnGetCommentsSuccess(boolean check);
        void OnGetCommentsFailure(String error);
    }
    public interface Presenter {
        void HandleGetReactionCount(GroupPostInfo postInfo);
    }
}
