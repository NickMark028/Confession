package com.example.confession.presenters.user;

import com.example.confession.binders.user.GetNewsfeedBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.views.fragments.NewfeedsFragment;

import java.util.ArrayList;

public class GetNewsfeedPresenter implements GetNewsfeedBinder.Presenter {

	private final GetNewsfeedBinder.View view;

	public GetNewsfeedPresenter(GetNewsfeedBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleGetNewsfeed() {

		User user = User.GetInstance();
		ArrayList<GroupPostInfo> posts = user.GetNewsfeed();

		if (posts != null)
			view.OnGetNewsfeedSuccess(posts);
		else
			view.OnGetNewsfeedFailure("Failed to get newsfeed");
	}
}
