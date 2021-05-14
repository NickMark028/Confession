package com.example.confession.presenters.post;

import com.example.confession.binders.post.ReactPostBinder;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.GroupPostInfo;

public class ReactPostPresenter implements ReactPostBinder.Presenter {
    private final ReactPostBinder.View view;

    public ReactPostPresenter(ReactPostBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandleReactPost(GroupPostInfo postInfo) {

        GroupPost post = new GroupPost(postInfo);

        boolean check = post.React(User.GetInstance().GetID(), User.GetAuthToken());
        if (check != true) {
            view.OnReactPostSuccess(check);
        } else {
            view.OnReactPostFailure("Failed to get reaction");
        }
    }
}
