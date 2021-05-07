package com.example.confession.binders.group;

import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;

public interface AddPostBinder {

	interface View {

		void AddPostSuccess(GroupPost post);
		void AddPostFailure(String error);
	}

	interface Presenter {

		void HandleAddPost(GroupPostInfo post_info, String content);
	}
}
