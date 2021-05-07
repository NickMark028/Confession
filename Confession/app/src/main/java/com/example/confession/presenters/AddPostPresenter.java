package com.example.confession.presenters;

import com.example.confession.binders.AddPostTabBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;

public class AddPostPresenter implements AddPostTabBinder.Presenter {

    AddPostTabBinder.View view;

    public AddPostPresenter(AddPostTabBinder.View view){
        this.view = view;
    }


    @Override
    public void HandleAddPost(ConfessionGroupInfo group_info, String content) {

        BasicUserInfo basic_user_info = User.GetInstance().GetBasicUserInfo();
        ConfessionGroup group = new ConfessionGroup(group_info);
        GroupPostInfo post_info = new GroupPostInfo(group_info, basic_user_info, content);
        GroupPost post = group.AddPost(post_info, basic_user_info, User.GetAuthToken());

        if (post != null)
            view.AddPostSuccess(post);
        else
            view.AddPostFailure("Failed to add new post");
    }
}
