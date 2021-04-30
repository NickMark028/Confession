package com.example.confession.presenters.user;

import com.example.confession.binders.user.JoinedGroupsBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class JoinedGroupsPresenter implements JoinedGroupsBinder.Presenter {

	private final JoinedGroupsBinder.View view;

	public JoinedGroupsPresenter(JoinedGroupsBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleGetJoinedGroups() {
		User user = User.GetInstance();
		ArrayList<ConfessionGroupInfo> groups = user.GetFollowedGroups();

		if (groups != null)
			view.OnGetJoinedGroupsSuccess(groups);
		else
			view.OnGetJoinedGroupsFailure("Failed to get groups");
	}
}
