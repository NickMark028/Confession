package com.example.confession.presenters;

import com.example.confession.binders.CreateGroupBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;

public class CreateGroupPresenter implements CreateGroupBinder.Presenter {

	CreateGroupBinder.View view;

	public CreateGroupPresenter(CreateGroupBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleCreateGroup(String short_name, String name) {

		ConfessionGroupInfo info = new ConfessionGroupInfo(short_name, name);
		ConfessionGroup group = User.GetInstance().CreateGroup(info);

		if (group != null)
			view.OnCreateGroupSuccess(group);
		else
			view.OnCreateGroupFailure("Duplicate short name");
	}
}
