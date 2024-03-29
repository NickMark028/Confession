package com.example.confession.binders.user;

public interface JoinGroupBinder {

	interface View {

		void OnJoinGroupSuccess();
		void OnJoinGroupFailure(String error);
	}

	interface Presenter {

		void HandleJoinGroup(String group_id);
	}
}
