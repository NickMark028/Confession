package com.example.confession.presenters.group;

import com.example.confession.binders.group.GetPendingMembersBinder;
import com.example.confession.binders.group.ManagePendingMembersBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class ManagePendingMembersPresenter implements ManagePendingMembersBinder.Presenter {

	private final ManagePendingMembersBinder.View view;

	public ManagePendingMembersPresenter(ManagePendingMembersBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleAcceptPendingMembers(BasicUserInfo user_info, ConfessionGroupInfo group_info) {

		ConfessionGroup group = new ConfessionGroup(group_info);

		if (group.AcceptUser(user_info.id, User.GetAuthToken()))
			view.OnAcceptPendingMembersSuccess();
		else
			view.OnAcceptPendingMembersFailure("Failed to accept pending members");
	}

	@Override
	public void HandleRejectPendingMembers(BasicUserInfo user_info, ConfessionGroupInfo group_info) {

		ConfessionGroup group = new ConfessionGroup(group_info);

		if (group.RejectUser(user_info.id, User.GetAuthToken()))
			view.OnAcceptPendingMembersSuccess();
		else
			view.OnAcceptPendingMembersFailure("Failed to reject pending members");

	}
}
