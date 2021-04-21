package com.example.confession.binders;

import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface FollowedGroupsTabBinder {

	interface View {

		void OnGetFollowedGroupsSuccess(ArrayList<ConfessionGroupInfo> groups);
		void OnGetFollowedGroupsFailure(String error);
	}

	interface Presenter {

		void HandleGetFollowedGroups();
	}
}
