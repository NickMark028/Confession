package com.example.confession.models.behaviors;

import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class ConfessionGroup {

	protected final ConfessionGroupInfo group_info;
	protected ArrayList<GroupPost> posts;
	protected ArrayList<BasicUserInfo> members;

	public ConfessionGroup(ConfessionGroupInfo group_info) {
		this.group_info = group_info;
	}
}
