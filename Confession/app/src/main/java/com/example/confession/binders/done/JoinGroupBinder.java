package com.example.confession.binders.done;

import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.data.BasicUserInfo;

public interface JoinGroupBinder {

	interface View {

		void OnJoinGroupSuccess();
		void OnJoinGroupFailure(String error);
	}

	interface Presenter {

		void HandleJoinGroup(String group_id);
	}
}
