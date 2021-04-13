package com.example.confession.binders;

import com.example.confession.models.behaviors.GroupPost;

import java.util.ArrayList;

public interface CommentBinder {

	interface View {

		void OnGetPostsSuccess(ArrayList<GroupPost> posts);
		void OnGetPostsFailure(int error_code);
	}

	interface Presenter {

		void HandleGetPosts();
	}
}
