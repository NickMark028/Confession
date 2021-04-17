package com.example.confession.binders;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.data.ConfessionGroupInfo;

public interface AddPostTabBinder {

	interface View {

		void AddPostSuccess(GroupPost post);
		void AddPostFailure(String error);
	}

	interface Presenter {

		void HandleAddPost(ConfessionGroupInfo group_info, String content);
	}
}
