package com.example.confession.binders;

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
