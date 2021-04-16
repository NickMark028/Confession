package com.example.confession.binders;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.GroupPost;

import java.util.ArrayList;

public interface GroupBinder {

	interface View {

		void OnGetPostsSuccess(ArrayList<GroupPost> posts);
		void OnGetPostsFailure(String error);
	}

	interface Presenter {

		void HandleGetPosts(ConfessionGroup group);
	}
}
