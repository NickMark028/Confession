package com.example.confession.presenters.group_done;

import com.example.confession.binders.comment_done.GetPostsBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public class GetPostsPresenter implements GetPostsBinder {
    private final GetPostsBinder.View view;

    public GetPostsPresenter(GetPostsBinder.View view) {
        this.view = view;
    }
    @Override
    public void HandleGetPost(ConfessionGroupInfo group_info) {

        ConfessionGroup group = new ConfessionGroup(group_info);
        ArrayList<GroupPostInfo> users = group.GetPosts(User.GetAuthToken());

        if (users != null)
            view.OnGetPostsSuccess(users);
        else
            view.OnGetPostsFailure("Failed to get members");
    }
}
