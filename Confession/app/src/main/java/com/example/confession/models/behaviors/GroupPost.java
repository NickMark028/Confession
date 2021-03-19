package com.example.confession.models.behaviors;

import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class GroupPost {

	protected final GroupPost post_info;
	protected final ConfessionGroupInfo group;
	protected ArrayList<PostComment> comments;

	public GroupPost(GroupPost post_info, ConfessionGroupInfo group) {
		this.post_info = post_info;
		this.group = group;
	}
}
