package com.example.confession.presenters.user;

import com.example.confession.binders.user.JoinedGroupIDsBinder;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;
import java.util.Collection;

public class JoinedGroupsIDPresenter implements JoinedGroupIDsBinder.Presenter {

	private final JoinedGroupIDsBinder.View view;

	public JoinedGroupsIDPresenter(JoinedGroupIDsBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleGetJoinedGroupIDs() {

		User user = User.GetInstance();
		Collection<ConfessionGroupInfo> groups = user.GetJoinedGroups();

		if (groups != null)
		{
			Collection<String> group_ids = new ArrayList<>(groups.size());
			for (ConfessionGroupInfo group : groups)
				group_ids.add(group.id);

			view.OnGetJoinedGroupIDsSuccess(group_ids);
		}
		else
		{
			view.OnGetJoinedGroupIDsFailure("Failed to get joined group ids");
		}
	}
}
