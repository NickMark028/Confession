package com.example.confession.presenters.done;

import com.example.confession.binders.done.JoinGroupBinder;
import com.example.confession.binders.done.LeaveGroupBinder;
import com.example.confession.binders.done.RemoveGroupBinder;
import com.example.confession.models.behaviors.User;

public class RemoveGroupPresenter implements RemoveGroupBinder.Presenter {

	private final RemoveGroupBinder.View view;

	public RemoveGroupPresenter(RemoveGroupBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleRemoveGroup(String group_id) {

		User user = User.GetInstance();

		if (!user.RemoveGroup(group_id))
			view.OnRemoveGroupSuccess();
		else
			view.OnRemoveGroupFailure("Failed to leave group");
	}
}
