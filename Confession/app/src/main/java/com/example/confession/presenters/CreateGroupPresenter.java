package com.example.confession.presenters;

import com.example.confession.binders.CreateGroupBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;

public class CreateGroupPresenter implements CreateGroupBinder.Presenter {
    CreateGroupBinder.View view;
    User user;
    @Override
    public void HandleCreateGroup(String group_name) {
        //....
        //user.CreateGroup();
        view.OnCreateGroupSuccess(200);

    }
}
