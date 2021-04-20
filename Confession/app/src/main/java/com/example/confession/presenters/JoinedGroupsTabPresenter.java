package com.example.confession.presenters;

import com.example.confession.binders.JoinedGroupsTabBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class JoinedGroupsTabPresenter implements JoinedGroupsTabBinder.Presenter {

	JoinedGroupsTabBinder.View view;

	public JoinedGroupsTabPresenter(JoinedGroupsTabBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleGetFollowedGroups() {

		User user = User.GetInstance();
		ArrayList<ConfessionGroupInfo> followed_groups = user.GetFollowedGroups();

		if (followed_groups != null)
			view.OnGetFollowedGroupsSuccess(followed_groups);
		else
			view.OnGetFollowedGroupsFailure("Failed to get owned groups");
	}

	@Override
	public void HandleGetCreatedGroups() {

		User user = User.GetInstance();
		ArrayList<ConfessionGroupInfo> created_groups = user.GetCreatedGroups();

		if (created_groups != null)
			view.OnGetCreatedGroupsSuccess(created_groups);
		else
			view.OnGetCreatedGroupsFailure("Failed to get owned groups");
	}

	@Override
	public void HandleLeaveGroup(ConfessionGroupInfo group) {

		User user = User.GetInstance();

		if (user.LeaveGroup(group.id))
			view.OnLeaveGroupSuccess(group);
		else
			view.OnLeaveGroupFailure("Failed to leave group");
	}
}
