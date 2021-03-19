package com.example.confession.models.data;

public final class PostCommentInfo {

	public final String id;
	public final BasicUserInfo commenter;
	public final String content;

	public PostCommentInfo(String id, BasicUserInfo commenter, String content) {

		this.id = id;
		this.commenter = commenter;
		this.content = content;
	}
}
