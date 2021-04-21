package com.example.confession.presenters.group_done;

import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public class GetPostsPresenter {
    interface View {

        void OnGetMembersSuccess(ArrayList<GroupPostInfo> groups);
        void OnGetMembersFailure(String error);
    }

    interface Presenter {

        void HandleGetPosts(ConfessionGroupInfo group_info);
    }
}
