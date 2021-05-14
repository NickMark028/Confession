package com.example.confession.binders.post;

import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;

import java.util.ArrayList;

public interface ReactPostBinder {
    interface View {

        void OnReactPostSuccess(boolean check);
        void OnReactPostFailure(String error);
    }
    interface Presenter {

        void HandleReactPost(GroupPostInfo postInfo);
    }
}
