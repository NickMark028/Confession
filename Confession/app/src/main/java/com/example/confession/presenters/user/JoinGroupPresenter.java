package com.example.confession.presenters.user;

import com.example.confession.binders.user.JoinGroupBinder;
import com.example.confession.models.behaviors.User;

public class JoinGroupPresenter implements JoinGroupBinder.Presenter {

	private final JoinGroupBinder.View view;

	public JoinGroupPresenter(JoinGroupBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleJoinGroup(String group_id) {

		User user = User.GetInstance();

		if (!user.JoinGroup(group_id))
			view.OnJoinGroupSuccess();
		else
			view.OnJoinGroupFailure("Failed to join group");
	}
}
