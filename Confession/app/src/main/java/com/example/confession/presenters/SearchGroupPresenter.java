package com.example.confession.presenters;

import android.content.Context;

import com.example.confession.binders.SearchGroupBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class SearchGroupPresenter implements SearchGroupBinder.Presenter {

	Context context;
	ConfessionGroup group;

	public SearchGroupPresenter(Context context) {
		this.context = context;
	}

	@Override
	public ArrayList<ConfessionGroupInfo> FindGroup(String shortname) {

		return null;
		//return ConfessionGroup.Find(shortname, context);
	}
}
