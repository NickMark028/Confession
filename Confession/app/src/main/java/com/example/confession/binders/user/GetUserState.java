package com.example.confession.binders.user;

import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.UserState;

import java.util.ArrayList;

public interface GetUserState {

	interface View {

		void OnGetUserStateSuccess(UserState user_state);
		void OnGetUserStateFailure(String error);
	}

	interface Presenter {

		void HandleGetUserState(ConfessionGroupInfo group);
	}
}
