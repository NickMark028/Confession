package com.example.confession.binders;

import com.example.confession.models.behaviors.GroupPost;

import java.util.ArrayList;

public interface NewsfeedTabBinder {

	interface View {

		void OnGetPostsSuccess(ArrayList<GroupPost> posts);
		void OnGetPostsFailure(int error_code);

		void OnReactPostSuccess(GroupPost post);
		void OnReactPostFailure(int error_code);

		void OnGetCommentsSuccess(GroupPost post);
		void OnGetCommentsFailure(int error_code);
	}

	interface Presenter {

		void HandleGetPosts();
		void HandleReactPost(GroupPost post);
		void HandleGetComments(GroupPost post);
	}
}
