package com.example.confession.binders.user;

import java.util.Collection;

public interface JoinedGroupIDsBinder {

	interface View {

		void OnGetJoinedGroupIDsSuccess(Collection<String> groups);
		void OnGetJoinedGroupIDsFailure(String error);
	}

	interface Presenter {

		void HandleGetJoinedGroupIDs();
	}
}
