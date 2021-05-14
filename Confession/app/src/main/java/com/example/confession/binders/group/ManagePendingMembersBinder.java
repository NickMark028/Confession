package com.example.confession.binders.group;

import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

public interface ManagePendingMembersBinder {

	interface View {

		void OnAcceptPendingMembersSuccess(android.view.View view);
		void OnAcceptPendingMembersFailure(android.view.View view, String error);

		void OnRejectPendingMembersSuccess(android.view.View view);
		void OnRejectPendingMembersFailure(android.view.View view, String error);
	}

	interface Presenter {

		void HandleAcceptPendingMembers(BasicUserInfo user_info, ConfessionGroupInfo group_info, android.view.View view);
		void HandleRejectPendingMembers(BasicUserInfo user_info, ConfessionGroupInfo group_info, android.view.View view);
	}
}
