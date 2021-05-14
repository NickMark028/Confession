package com.example.confession.binders.user;

import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public interface GetNewsfeedBinder {

	interface View {

		void OnGetNewsfeedSuccess(ArrayList<GroupPostInfo> posts);
		void OnGetNewsfeedFailure(String error);
	}

	interface Presenter {

		void HandleGetNewsfeed();
	}
}
