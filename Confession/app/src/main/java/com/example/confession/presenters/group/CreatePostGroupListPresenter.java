package com.example.confession.presenters.group;

import com.example.confession.binders.group.CreatePostGroupListBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class CreatePostGroupListPresenter implements CreatePostGroupListBinder.Presenter {

	CreatePostGroupListBinder.View view;

	public CreatePostGroupListPresenter(CreatePostGroupListBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleGetGroups() {

		User user = User.GetInstance();
		ArrayList<ConfessionGroupInfo> groups = user.GetJoinedGroups();

		if (groups != null)
			view.OnGetGroupsSuccess(groups);
		else
			view.OnGetGroupsFailure("Failed to get groups");
	}
}
