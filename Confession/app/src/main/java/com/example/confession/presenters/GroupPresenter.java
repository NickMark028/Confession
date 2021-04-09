package com.example.confession.presenters;

import com.example.confession.binders.GroupBinder;
import com.example.confession.binders.SignUpBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.UserInfo;

import java.security.acl.Group;

public class GroupPresenter implements GroupBinder.Presenter {

	private final GroupBinder.View view;

	public GroupPresenter(GroupBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleGetPosts() {

//		GroupPost.
	}
}
