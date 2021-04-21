package com.example.confession.binders.comment_done;

import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public interface GetPostsBinder {
    void HandleGetPost(ConfessionGroupInfo group_info);

    interface View {

        void OnGetPostsSuccess(ArrayList<GroupPostInfo> groups);
        void OnGetPostsFailure(String error);
    }

    interface Presenter {

        void HandleGetPosts(ConfessionGroupInfo group_info);
    }
}
