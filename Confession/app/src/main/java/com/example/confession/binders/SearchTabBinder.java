package com.example.confession.binders;

import androidx.annotation.NonNull;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface SearchTabBinder {

	interface View {

		void OnFindGroupSuccess(ArrayList<ConfessionGroupInfo> groups);
		void OnFindGroupFailure(int error_code);

		void OnJoinGroupSuccess(ConfessionGroup group);
		void OnJoinGroupFailure(int error_code);
	}

	interface Presenter {

		void HandleFindGroup(String group_name);
		void HandleJoinGroup(ConfessionGroupInfo group_info);
	}
}
