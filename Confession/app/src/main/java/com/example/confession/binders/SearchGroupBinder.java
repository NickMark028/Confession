package com.example.confession.binders;

import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface SearchGroupBinder {

	interface View {

		void Show(ArrayList<ConfessionGroupInfo> groups);
	}

	interface Presenter {

		ArrayList<ConfessionGroupInfo> FindGroup(String shortname);
	}
}
