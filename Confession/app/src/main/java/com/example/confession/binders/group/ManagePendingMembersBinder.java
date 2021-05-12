package com.example.confession.binders.group;

import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface ManagePendingMembersBinder {

	interface View {

		void OnAcceptAllPendingMembersSuccess();

		void OnAcceptPendingMembersSuccess();

		void OnAcceptPendingMembersFailure(String error);
	}

	interface Presenter {

		void HandleAcceptPendingMembers(BasicUserInfo user_info, ConfessionGroupInfo group_info);
	}
}
