package com.example.confession.presenters.user;

import com.example.confession.binders.user.LeaveGroupBinder;
import com.example.confession.models.behaviors.User;

public class LeaveGroupPresenter implements LeaveGroupBinder.Presenter {

	private final LeaveGroupBinder.View view;

	public LeaveGroupPresenter(LeaveGroupBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleLeaveGroup(String group_id) {

		User user = User.GetInstance();

		if (!user.LeaveGroup(group_id))
			view.OnLeaveGroupSuccess();
		else
			view.OnLeaveGroupFailure("Failed to leave group");
	}
}
