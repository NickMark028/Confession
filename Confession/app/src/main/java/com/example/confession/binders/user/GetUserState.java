package com.example.confession.binders.user;

import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.UserState;

import java.util.ArrayList;

public interface GetUserState {



	interface Presenter {

		void HandleGetUserState(ConfessionGroupInfo group);
	}
}
