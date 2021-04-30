package com.example.confession.models.data;

import com.example.confession.models.behaviors.GroupPost;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public final class PostCommentInfo implements Serializable {

	public final String id;
	public final GroupPostInfo post;         // TODO them cai nay vao constructor
	public final BasicUserInfo commenter;
	public final String content;
	public final Date time_created;

	public PostCommentInfo(BasicUserInfo commenter, String content) {

		this("", commenter, content);
	}

	public PostCommentInfo(String id, BasicUserInfo commenter, String content) {

		this.id = id;
		this.post = null;            // TODO Sua lai
		this.commenter = commenter;
		this.content = content;
		time_created = Calendar.getInstance().getTime();
	}

	public PostCommentInfo(String id, GroupPostInfo post, BasicUserInfo commenter, String content) {

		this.id = id;
		this.post = post;
		this.commenter = commenter;
		this.content = content;
		time_created = Calendar.getInstance().getTime();
	}

	@Override
	public String toString() {
		return "PostCommentInfo{" +
				"id='" + id + '\'' +
				", commenter=" + commenter +
				", content='" + content + '\'' +
				'}';
	}
}
