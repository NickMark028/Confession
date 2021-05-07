package com.example.confession.presenters.group;

import com.example.confession.binders.group.AddPostBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.GroupPostInfo;

public class AddPostPresenter implements AddPostBinder.Presenter {

	private final AddPostBinder.View view;

	public AddPostPresenter(AddPostBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleAddPost(GroupPostInfo post_info, String content) {

		if (content.isEmpty())
		{
			view.AddPostFailure("Content can't be empty");
			return;
		}

		User user = User.GetInstance();
		ConfessionGroup group = new ConfessionGroup(post_info.group);
		GroupPost post = group.AddPost(post_info, user.GetBasicUserInfo(), user.GetAuthToken());

		if (post != null)
			view.AddPostSuccess(post);
		else
			view.AddPostFailure("Failed to add post");
	}
}
