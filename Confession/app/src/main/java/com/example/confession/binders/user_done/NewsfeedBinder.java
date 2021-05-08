package com.example.confession.binders.user_done;

import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public interface NewsfeedBinder {

	interface View {

		void OnGetNewsfeedSuccess(ArrayList<GroupPostInfo> posts);
		void OnGetNewsfeedFailure(String error);
	}

	interface Presenter {

		void HandleGetNewsfeed();
	}
}
