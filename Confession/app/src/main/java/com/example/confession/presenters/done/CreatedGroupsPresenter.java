package com.example.confession.presenters.done;

import com.example.confession.binders.CreatePostGroupListBinder;
import com.example.confession.binders.done.CreatedGroupsBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class CreatedGroupsPresenter implements CreatedGroupsBinder.Presenter {

	private final CreatePostGroupListBinder.View view;

	public CreatedGroupsPresenter(CreatePostGroupListBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleGetCreatedGroups() {

		User user = User.GetInstance();
		ArrayList<ConfessionGroupInfo> groups = user.GetCreatedGroups();

		if (groups != null)
			view.OnGetGroupsSuccess(groups);
		else
			view.OnGetGroupsFailure("Failed to get created groups");

	}
}
