package com.example.confession.binders.post;

import com.example.confession.models.data.PostCommentInfo;

import java.util.ArrayList;

public interface GetReactionCountBinder {
    interface View {

        void OnGetCommentsSuccess(int Count);
        void OnGetCommentsFailure(String error);
    }
    public interface Presenter {
        void HandleGetReactionCount(int count);
    }
}
