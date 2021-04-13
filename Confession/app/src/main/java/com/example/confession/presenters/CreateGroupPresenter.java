package com.example.confession.presenters;

import com.example.confession.binders.CreateGroupBinder;

public class CreateGroupPresenter implements CreateGroupBinder.Presenter {
    CreateGroupBinder.View view;

    @Override
    public void HandleCreateGroup(String group_name) {
        //....
        view.OnCreateGroupSuccess(200);
    }
}
