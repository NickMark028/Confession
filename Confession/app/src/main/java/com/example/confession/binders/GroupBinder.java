package com.example.confession.binders;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public interface GroupBinder {

	interface View {

		void OnGetPostsSuccess(ArrayList<GroupPostInfo> posts);
		void OnGetPostsFailure(String error);
	}

	interface Presenter {

		void HandleGetPosts(ConfessionGroup group);
	}
}
