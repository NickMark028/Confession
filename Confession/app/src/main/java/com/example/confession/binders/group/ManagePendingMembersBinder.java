package com.example.confession.binders.group;

import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface ManagePendingMembersBinder {

	interface View {

		void OnAcceptPendingMembersSuccess();
		void OnAcceptPendingMembersFailure(String error);

		void OnRejectPendingMembersSuccess();
		void OnRejectPendingMembersFailure(String error);
	}

	interface Presenter {

		void HandleAcceptPendingMembers(BasicUserInfo user_info, ConfessionGroupInfo group_info);
		void HandleRejectPendingMembers(BasicUserInfo user_info, ConfessionGroupInfo group_info);
	}
}
