package com.example.confession.presenters;

import com.example.confession.binders.GroupBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public class GroupPresenter implements GroupBinder.Presenter {

	private final GroupBinder.View view;

	public GroupPresenter(GroupBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleGetPosts(ConfessionGroup group) {

		ArrayList<GroupPostInfo> posts = group.GetPosts(User.GetAuthToken());

		if (posts != null)
			view.OnGetPostsSuccess(posts);
		else
			view.OnGetPostsFailure("Failed to get groups");
	}
}
