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

	public GroupPostInfo(String id, BasicUserInfo author, BasicUserInfo approver, String content) {

		this.id = id;
		this.time_created = Calendar.getInstance().getTime();
		this.author = author;
		this.approver = approver;
		this.content = content;
	}
}
