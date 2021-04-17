package com.example.confession.binders;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface CreatePostGroupListBinder {

	interface View {

		void OnGetGroupsSuccess(ArrayList<ConfessionGroup> groups);
		void OnGetGroupsFailure(String error);
	}

	interface Presenter {

		void HandleGetGroups();
	}
}
