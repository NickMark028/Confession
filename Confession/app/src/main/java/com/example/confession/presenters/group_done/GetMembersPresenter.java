package com.example.confession.presenters.group_done;

import com.example.confession.binders.comment_done.GetMembersBinder;
import com.example.confession.binders.user_done.FollowedGroupsBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class GetMembersPresenter implements GetMembersBinder.Presenter {

	private final GetMembersBinder.View view;

	public GetMembersPresenter(GetMembersBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleGetMembers(ConfessionGroupInfo group_info) {

		ConfessionGroup group = new ConfessionGroup(group_info);
		ArrayList<BasicUserInfo> users = group.GetMembers(User.GetAuthToken());

		if (users != null)
			view.OnGetMembersSuccess(users);
		else
			view.OnGetMembersFailure("Failed to get members");
	}
}
