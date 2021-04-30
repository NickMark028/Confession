package com.example.confession.binders.group;

import androidx.annotation.NonNull;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;
import java.util.Set;

public interface SearchGroupBinder {

	interface View {

		void OnFindGroupSuccess(ArrayList<ConfessionGroupInfo> groups);
		void OnFindGroupFailure(String error);

		void OnGetJoinedGroupsSuccess(Set<String> joined_groups);
		void OnGetJoinedGroupsFailure(String error);
	}

	interface Presenter {

		void HandleFindGroup(String group_name);
		void HandleGetJoinedGroups();
	}
}
