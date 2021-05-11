package com.example.confession.binders.group;

import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface GetPendingMembersBinder {

	interface View {

		void OnGetPendingMembersSuccess(ArrayList<BasicUserInfo> members);
		void OnGetPendingMembersFailure(String error);
	}

	interface Presenter {

		void HandleGetPendingMembers(ConfessionGroupInfo group_info);
	}
}
