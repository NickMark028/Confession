package com.example.confession.presenters.group;

import com.example.confession.binders.group.GetPendingMembersBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class GetPendingMembersPresenter implements GetPendingMembersBinder.Presenter {

	private final GetPendingMembersBinder.View view;

	public GetPendingMembersPresenter(GetPendingMembersBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleGetPendingMembers(ConfessionGroupInfo group_info) {

		ConfessionGroup group = new ConfessionGroup(group_info);

		ArrayList<BasicUserInfo> pending_members = group.GetPendingUsers(User.GetAuthToken());

		if (pending_members != null)
			view.OnGetPendingMembersSuccess(pending_members);
		else
			view.OnGetPendingMembersFailure("Failed to get pending members");
	}
}
