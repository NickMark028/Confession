package com.example.confession.binders;

import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface CreatedGroupsBinder {

	interface View {

		void OnGetCreatedGroupsSuccess(ArrayList<ConfessionGroupInfo> groups);
		void OnGetCreatedGroupsFailure(String error);
	}

	interface Presenter {

		void HandleGetCreatedGroups();
	}
}
