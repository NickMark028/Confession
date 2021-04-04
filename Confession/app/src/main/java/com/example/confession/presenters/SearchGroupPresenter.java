package com.example.confession.presenters;

import android.content.Context;

import com.example.confession.binders.SearchTabBinder;
import com.example.confession.models.behaviors.ConfessionGroup;

public class SearchGroupPresenter implements SearchTabBinder.Presenter {

	Context context;

	public SearchGroupPresenter(Context context) {
		this.context = context;
	}

	@Override
	public void HandleFindGroup(String group_name) {

		ConfessionGroup.Find(group_name, context);
	}
}
