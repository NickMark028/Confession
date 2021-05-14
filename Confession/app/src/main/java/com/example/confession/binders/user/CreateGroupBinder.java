package com.example.confession.binders.user;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.ConfessionGroupInfo;

public interface CreateGroupBinder {

	interface View {

		void OnCreateGroupSuccess(ConfessionGroup group);
		void OnCreateGroupFailure(String error);
	}

	interface Presenter {

		void HandleCreateGroup(String short_name, String name);
	}
}
