package com.example.confession.models.data;

public final class GroupPostInfo {

	public final String id;
	public final BasicUserInfo author;
	public final BasicUserInfo approver;
	public final String content;

	public GroupPostInfo(String id, BasicUserInfo author, BasicUserInfo approver, String content) {

		this.id = id;
		this.author = author;
		this.approver = approver;
		this.content = content;
	}
}
