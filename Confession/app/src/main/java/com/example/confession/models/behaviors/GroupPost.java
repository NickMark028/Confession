package com.example.confession.models.behaviors;

import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public class GroupPost {

	protected final GroupPostInfo post_info;
	protected final ConfessionGroupInfo group;
	protected ArrayList<PostComment> comments;

	public GroupPost(GroupPostInfo post_info, ConfessionGroupInfo group) {
		this.post_info = post_info;
		this.group = group;
	}
}
