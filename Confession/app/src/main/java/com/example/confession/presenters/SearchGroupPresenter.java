package com.example.confession.presenters;

import android.content.Context;

import com.example.confession.binders.SearchTabBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class SearchGroupPresenter implements SearchTabBinder.Presenter {

	SearchTabBinder.View view;

	public SearchGroupPresenter(SearchTabBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleFindGroup(String group_name) {

		ArrayList<ConfessionGroupInfo> groups = ConfessionGroup.Find(group_name);
		view.OnFindGroupSuccess(groups);
	}

	@Override
	public void HandleGetJoinedGroups() {


	}

//	@Override
//	public void HandleJoinGroup(ConfessionGroupInfo group_info) {
//
//		return null;
//	}
}
