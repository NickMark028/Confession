package com.example.confession.binders.done;

public interface RemoveGroupBinder {

	interface View {

		void OnRemoveGroupSuccess();
		void OnRemoveGroupFailure(String error);
	}

	interface Presenter {

		void HandleRemoveGroup(String group_id);
	}
}
