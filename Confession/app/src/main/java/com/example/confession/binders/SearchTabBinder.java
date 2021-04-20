package com.example.confession.binders;

import androidx.annotation.NonNull;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;
import java.util.Set;

public interface SearchTabBinder {

	interface View {

		void OnFindGroupSuccess(ArrayList<ConfessionGroupInfo> groups);
		void OnFindGroupFailure(String error);

		void OnGetJoinedGroupsSuccess(Set<String> joined_groups);
		void OnGetJoinedGroupsFailure(String error);

//		void OnJoinGroupSuccess(ConfessionGroup group);
//		void OnJoinGroupFailure(String error);
	}

	interface Presenter {

		void HandleFindGroup(String group_name);
		void HandleGetJoinedGroups();
//		void HandleJoinGroup(ConfessionGroupInfo group_info);
	}
}
