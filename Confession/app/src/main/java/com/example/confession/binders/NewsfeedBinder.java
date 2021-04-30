package com.example.confession.binders;

import com.example.confession.models.behaviors.GroupPost;

import java.util.ArrayList;

public interface NewsfeedBinder {

	interface View {

		void OnGetNewsfeedSuccess(ArrayList<GroupPost> posts);
		void OnGetNewsfeedFailure(String error);

		void OnReactPostSuccess(GroupPost post);
		void OnReactPostFailure(String error);

		void OnGetCommentsSuccess(GroupPost post);
		void OnGetCommentsFailure(String error);
	}

	interface Presenter {

		void HandleGetNewsfeed();
		void HandleReactPost(GroupPost post);
		void HandleGetComments(GroupPost post);
	}
}
