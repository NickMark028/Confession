package com.example.confession.presenters.user_done;

import com.example.confession.binders.user_done.FollowedGroupsBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class FollowedGroupsPresenter implements FollowedGroupsBinder.Presenter {

	private final FollowedGroupsBinder.View view;

	public FollowedGroupsPresenter(FollowedGroupsBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleGetFollowedGroups() {

		User user = User.GetInstance();
		ArrayList<ConfessionGroupInfo> groups = user.GetFollowedGroups();

		if (groups != null)
			view.OnGetFollowedGroupsSuccess(groups);
		else
			view.OnGetFollowedGroupsFailure("Failed to get followed groups");
	}
}
