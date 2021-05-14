package com.example.confession.binders.user;

public interface RemoveGroupBinder {

	interface View {

		
		void OnRemoveGroupFailure(String error);
	}

	interface Presenter {

		void HandleRemoveGroup(String group_id);
	}
}
