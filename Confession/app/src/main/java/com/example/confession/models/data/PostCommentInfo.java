package com.example.confession.models.data;

import java.io.Serializable;

public final class PostCommentInfo implements Serializable {

	public final String id;
	public final BasicUserInfo commenter;
	public final String content;

	public PostCommentInfo(String id, BasicUserInfo commenter, String content) {

		this.id = id;
		this.commenter = commenter;
		this.content = content;
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
