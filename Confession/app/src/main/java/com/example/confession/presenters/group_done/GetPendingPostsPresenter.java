package com.example.confession.presenters.group_done;

import com.example.confession.binders.comment_done.GetPendingPostsBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public class GetPendingPostsPresenter implements GetPendingPostsBinder.Presenter {

    private final GetPendingPostsBinder.View view;

    public GetPendingPostsPresenter(GetPendingPostsBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandleGetPendingPosts(ConfessionGroupInfo group_info) {

        ConfessionGroup group = new ConfessionGroup(group_info);
        ArrayList<GroupPostInfo> posts = group.GetPosts(User.GetAuthToken());

        if (posts != null)
            view.OnGetPendingPostsSuccess(posts);
        else
            view.OnGetPendingPostsFailure("Failed to get posts");
    }


}
