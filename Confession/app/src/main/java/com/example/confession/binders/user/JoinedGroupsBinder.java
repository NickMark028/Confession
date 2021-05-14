package com.example.confession.binders.user;

import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface JoinedGroupsBinder {

	interface View {

		void OnGetJoinedGroupsSuccess(ArrayList<ConfessionGroupInfo> groups);
		void OnGetJoinedGroupsFailure(String error);
	}

	interface Presenter {

		void HandleGetJoinedGroups();
	}
}
