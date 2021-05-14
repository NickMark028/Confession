package com.example.confession.presenters.group;

import com.example.confession.binders.group.ManagePendingMembersBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import androidx.recyclerview.widget.RecyclerView;

public class ManagePendingMembersPresenter implements ManagePendingMembersBinder.Presenter {

	private final ManagePendingMembersBinder.View view;

	public ManagePendingMembersPresenter(ManagePendingMembersBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleAcceptPendingMembers(BasicUserInfo user_info, ConfessionGroupInfo group_info, RecyclerView.ViewHolder view_holder) {

		ConfessionGroup group = new ConfessionGroup(group_info);

		if (group.AcceptUser(user_info.id, User.GetAuthToken()))
			this.view.OnAcceptPendingMembersSuccess(view_holder);
		else
			this.view.OnAcceptPendingMembersFailure(view_holder, "Failed to accept pending members");
	}

	@Override
	public void HandleRejectPendingMembers(BasicUserInfo user_info, ConfessionGroupInfo group_info, RecyclerView.ViewHolder view_holder) {

		ConfessionGroup group = new ConfessionGroup(group_info);

		if (group.RejectPost(user_info.id, User.GetAuthToken()))
			this.view.OnAcceptPendingMembersSuccess(view_holder);
		else
			this.view.OnAcceptPendingMembersFailure(view_holder, "Failed to reject pending members");
	}
}
