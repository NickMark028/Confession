package com.example.confession.binders;

import androidx.dynamicanimation.animation.SpringAnimation;

import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class JoinedGroupsTabPresenter implements JoinedGroupsTabBinder.Presenter {

	private final JoinedGroupsTabBinder.View view;

	public JoinedGroupsTabPresenter(JoinedGroupsTabBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleGetFollowedGroups() {

		User user = User.GetInstance();
		ArrayList<ConfessionGroupInfo> groups = user.GetFollowedGroups();

		if (groups != null)
			view.OnGetFollowedGroupsSuccess(groups);
		else
			view.OnGetFollowedGroupsFailure("Failed to get groups");
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
