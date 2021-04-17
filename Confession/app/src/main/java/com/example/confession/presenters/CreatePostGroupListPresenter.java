package com.example.confession.presenters;

import com.example.confession.binders.CreatePostGroupListBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;

import java.util.ArrayList;

public class CreatePostGroupListPresenter implements CreatePostGroupListBinder.Presenter {

	CreatePostGroupListBinder.View view;

	public CreatePostGroupListPresenter(CreatePostGroupListBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleGetGroups() {

		User user = User.GetInstance();
		ArrayList<ConfessionGroup> groups = user.GetFollowedGroups();

		if (groups != null)
			view.OnGetGroupsSuccess(groups);
		else
			view.OnGetGroupsFailure("Failed to get followed groups");
	}
}
