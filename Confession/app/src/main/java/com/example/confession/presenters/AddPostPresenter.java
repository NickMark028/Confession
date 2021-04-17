package com.example.confession.presenters;

import com.example.confession.binders.AddPostTabBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;

public class AddPostPresenter implements AddPostTabBinder.Presenter {

    AddPostTabBinder.View view;

    public AddPostPresenter(AddPostTabBinder.View view){
        this.view = view;
    }


    @Override
    public void HandleAddPost(ConfessionGroupInfo group_info, String content) {

        User user = User.GetInstance();
        GroupPostInfo post_info = new GroupPostInfo(user.GetBasicUserInfo(), null, content);
        GroupPost post = new ConfessionGroup(group_info).AddPost(post_info, user.GetBasicUserInfo(), User.GetAuthToken());

        if (post != null)
            view.AddPostSuccess(post);
        else
            view.AddPostFailure("Failed to add new post");
    }
}
