package com.example.confession.presenters;

import com.example.confession.binders.AddPostTabBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.GroupPostInfo;

public class AddPostPresenter implements AddPostTabBinder.Presenter {

    AddPostTabBinder.View view;

    public AddPostPresenter(AddPostTabBinder.View view){
        this.view = view;
    }


    @Override
    public void HandleAddPost(ConfessionGroup group, String content) {

        User user = User.GetInstance();
        GroupPostInfo info = new GroupPostInfo(user.GetBasicUserInfo(), null, content);
        GroupPost post = group.AddPost(info, user.GetBasicUserInfo());

        if (post != null)
            view.AddPostSuccess(post);
        else
            view.AddPostFailure("Failed to add new post");
    }
}
