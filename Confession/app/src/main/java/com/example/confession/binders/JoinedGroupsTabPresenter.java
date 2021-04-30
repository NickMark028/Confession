package com.example.confession.binders;

import androidx.dynamicanimation.animation.SpringAnimation;

import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class JoinedGroupsTabPresenter implements JoinedGroupsTabBinder.Presenter {

	private final JoinedGroupsTabBinder.View view;

	public JoinedGroupsTabPresenter(JoinedGroupsTabBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleGetFollowedGroups() {


	}

	@Override
	public void HandleGetCreatedGroups() {


	}
}
