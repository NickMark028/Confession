package com.example.confession.binders;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;

import java.util.ArrayList;

public interface FollowedGroupsTabBinder {

	interface View {

		// ...

		void OnGetFollowedGroupsSuccess(ArrayList<ConfessionGroup> groups);
		void OnGetFollowedGroupsFailure(int error_code);

		void OnGetCreatedGroupsSuccess(ArrayList<ConfessionGroup> groups);
		void OnGetCreatedGroupsFailure(int error_code);

		void OnUnfollowGroupSuccess(ConfessionGroup group);
		void OnUnfollowGroupFailure(int error_code);

	}

	interface Presenter {

		void HandleGetFollowedGroups(User user);
		void HandleGetCreatedGroups(User user);
		void HandleUnfollowGroup(ConfessionGroup group, User user);
	}
}
