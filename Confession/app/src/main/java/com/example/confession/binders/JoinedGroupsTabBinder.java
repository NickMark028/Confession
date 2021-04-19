package com.example.confession.binders;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface JoinedGroupsTabBinder {

	interface View {

		// ...

		void OnGetFollowedGroupsSuccess(ArrayList<ConfessionGroupInfo> groups);
		void OnGetFollowedGroupsFailure(String error);

		void OnGetCreatedGroupsSuccess(ArrayList<ConfessionGroupInfo> groups);
		void OnGetCreatedGroupsFailure(String error);

		void OnLeaveGroupSuccess(ConfessionGroupInfo group);
		void OnLeaveGroupFailure(String error);

	}

	interface Presenter {

		void HandleGetFollowedGroups();
		void HandleGetCreatedGroups();
		void HandleLeaveGroup(ConfessionGroupInfo group);
	}
}
