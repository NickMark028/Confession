package com.example.confession.models.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public final class GroupPostInfo implements Serializable {

	public final String id;
	public final Date time_created;
	public final BasicUserInfo author;
	public final BasicUserInfo approver;
	public final String content;

	public GroupPostInfo(BasicUserInfo author, BasicUserInfo approver, String content) {

		this("", author, approver, content);
	}

	public GroupPostInfo(String id, BasicUserInfo author, BasicUserInfo approver, String content) {

		this.id = id;
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
