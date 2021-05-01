package com.example.confession.presenters.group;

import com.example.confession.binders.group.SearchGroupBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class SearchGroupPresenter implements SearchGroupBinder.Presenter {

	SearchGroupBinder.View view;

	public SearchGroupPresenter(SearchGroupBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleFindGroup(String group_name) {

		ArrayList<ConfessionGroupInfo> groups = ConfessionGroup.Find(group_name);

		if (groups != null)
			view.OnFindGroupSuccess(groups);
		else
			view.OnFindGroupFailure("Failed to get groups");
	}
}
