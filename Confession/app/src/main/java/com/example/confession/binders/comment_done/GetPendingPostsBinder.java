package com.example.confession.binders.comment_done;

import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public interface GetPendingPostsBinder {
    void HandleGetPendingPosts(ConfessionGroupInfo group_info);

    interface View {

        void OnGetPendingPostsSuccess(ArrayList<GroupPostInfo> groups);
        void OnGetPendingPostsFailure(String error);
    }

    interface Presenter {

        void HandleGetPendingPosts(ConfessionGroupInfo group_info);
    }
}
