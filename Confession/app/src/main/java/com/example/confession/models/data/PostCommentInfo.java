package com.example.confession.models.data;

import com.example.confession.models.behaviors.GroupPost;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public final class PostCommentInfo implements Serializable {

	public final String id;
	public final GroupPostInfo post;
	public final BasicUserInfo commenter;
	public final String content;
	public final Date time_created;

	public PostCommentInfo(GroupPostInfo post, BasicUserInfo commenter, String content) {

		this(null, post, commenter, content);
	}

	public PostCommentInfo(String id, GroupPostInfo post, BasicUserInfo commenter, String content) {

		this.id = id;
		this.post = post;
		this.commenter = commenter;
		this.content = content;
		time_created = Calendar.getInstance().getTime();        // TODO might fix this for later
	}
}
