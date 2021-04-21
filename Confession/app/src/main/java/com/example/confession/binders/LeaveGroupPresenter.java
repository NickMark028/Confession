package com.example.confession.binders;

import com.example.confession.models.behaviors.User;

public class LeaveGroupPresenter implements LeaveGroupBinder.Presenter {

	private final JoinGroupBinder.View view;

	public LeaveGroupPresenter(JoinGroupBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleLeaveGroup(String group_id) {

		User user = User.GetInstance();

		if (!user.LeaveGroup(group_id))
			view.OnJoinGroupSuccess();
		else
			view.OnJoinGroupFailure("Failed to join group");
	}
}
