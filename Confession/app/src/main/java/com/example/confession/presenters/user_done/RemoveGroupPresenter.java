package com.example.confession.presenters.user_done;

import com.example.confession.binders.user_done.RemoveGroupBinder;
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
