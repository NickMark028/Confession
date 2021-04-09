package com.example.confession.binders;

import com.example.confession.models.behaviors.GroupPost;

public interface AddPostTabBinder {

	interface View {

		void AddPostSuccess(GroupPost post);
		void AddPostFailure(int error_code);
	}

	interface Presenter {

		//void HandleLogin(String username, String password);
	}
}
