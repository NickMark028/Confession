package com.example.confession.binders.user;

import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.UserState;

public class GetUserStatePresenter implements GetUserState.Presenter {

	private final GetUserState.View view;

	public GetUserStatePresenter(GetUserState.View view) {
		this.view = view;
	}

	@Override
	public void HandleGetUserState(ConfessionGroupInfo group) {

		User user = User.GetInstance();
		UserState state = user.GetState(group.id);

		if (!state.equals(UserState.Undefined))
			view.OnGetUserStateSuccess(state);
		else
			view.OnGetUserStateFailure("Unable to get user state");
	}
}
