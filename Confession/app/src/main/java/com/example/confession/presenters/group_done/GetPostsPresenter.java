package com.example.confession.presenters.group_done;

import com.example.confession.binders.comment_done.GetPostsBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public class GetPostsPresenter implements GetPostsBinder.Presenter {

    private final GetPostsBinder.View view;

    public GetPostsPresenter(GetPostsBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandleGetPosts(ConfessionGroupInfo group_info) {

        ConfessionGroup group = new ConfessionGroup(group_info);
        ArrayList<GroupPostInfo> posts = group.GetPosts(User.GetAuthToken());

        if (posts != null)
            view.OnGetPostsSuccess(posts);
        else
            view.OnGetPostsFailure("Failed to get members");
    }
}
