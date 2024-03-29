package com.example.confession.binders.user;

public interface LeaveGroupBinder {

	interface View {

		void OnLeaveGroupSuccess();
		void OnLeaveGroupFailure(String error);
	}

	interface Presenter {

		void HandleLeaveGroup(String group_id);
	}
}
