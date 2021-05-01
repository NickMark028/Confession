package com.example.confession.binders.group;

import androidx.annotation.NonNull;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;
import java.util.Set;

public interface SearchGroupBinder {

	interface View {

		void OnFindGroupSuccess(ArrayList<ConfessionGroupInfo> groups);
		void OnFindGroupFailure(String error);
	}

	interface Presenter {

		void HandleFindGroup(String group_name);
	}
}
