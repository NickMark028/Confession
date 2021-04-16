package com.example.confession.binders;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.GroupPost;

public interface AddPostTabBinder {

	interface View {

		void AddPostSuccess(GroupPost post);
		void AddPostFailure(String error);
	}

	interface Presenter {

		void HandleAddPost(ConfessionGroup group, String content);
	}
}
