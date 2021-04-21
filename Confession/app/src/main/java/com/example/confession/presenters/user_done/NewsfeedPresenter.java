package com.example.confession.presenters.user_done;

import com.example.confession.binders.user_done.NewsfeedBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public class NewsfeedPresenter implements NewsfeedBinder.Presenter {

	private final NewsfeedBinder.View view;

	public NewsfeedPresenter(NewsfeedBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleGetNewsfeed(String group_id) {

		User user = User.GetInstance();
		ArrayList<GroupPostInfo> posts = user.GetNewsfeed();

		if (posts != null)
			view.OnGetNewsfeedSuccess(posts);
		else
			view.OnGetNewsfeedFailure("Failed to get newsfeed");
	}
}
