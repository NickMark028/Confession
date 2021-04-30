package com.example.confession.models.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public final class GroupPostInfo implements Serializable {

	public final String id;
	public final ConfessionGroupInfo group;      // TODO Them cai nay vao constructor
	public final Date time_created;
	private final BasicUserInfo author;
	public final BasicUserInfo approver;
	public final String content;

	// TODO Related
	public int reaction_count;
	public boolean react;

	public GroupPostInfo(BasicUserInfo author, BasicUserInfo approver, String content) {

		this("", author, approver, content);
	}

	public GroupPostInfo(String id, BasicUserInfo author, BasicUserInfo approver, String content) {

		this.id = id;
		this.group = null;          // TODO sua lai
		this.time_created = Calendar.getInstance().getTime();       // Todo Temp
		this.author = author;
		this.approver = approver;
		this.content = content;
	}

	public GroupPostInfo(String id, ConfessionGroupInfo group, BasicUserInfo author, BasicUserInfo approver, String content) {

		this.id = id;
		this.group = group;
		this.time_created = Calendar.getInstance().getTime();       // Todo Temp
		this.author = author;
		this.approver = approver;
		this.content = content;
	}

	@Override
	public String toString() {
		return "GroupPostInfo{" +
				"id='" + id + '\'' +
				", time_created=" + time_created +
				", author=" + author +
				", approver=" + approver +
				", content='" + content + '\'' +
				'}';
	}
}
