package com.example.confession.binders.group;

import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public interface GetPostsBinder {

    interface View {

        void OnGetPostsSuccess(ArrayList<GroupPostInfo> posts);
        void OnGetPostsFailure(String error);
    }

    interface Presenter {

        void HandleGetPosts(ConfessionGroupInfo group_info);
    }
}
