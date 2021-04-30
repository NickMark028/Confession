package com.example.confession.presenters.user;

import com.example.confession.binders.user.CreatedGroupsBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class CreatedGroupsPresenter implements CreatedGroupsBinder.Presenter {

	private final CreatedGroupsBinder.View view;

	public CreatedGroupsPresenter(CreatedGroupsBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleGetCreatedGroups() {

		User user = User.GetInstance();
		ArrayList<ConfessionGroupInfo> groups = user.GetCreatedGroups();

		if (groups != null)
			view.OnGetCreatedGroupsSuccess(groups);
		else
			view.OnGetCreatedGroupsFailure("Failed to get groups");
	}
}
