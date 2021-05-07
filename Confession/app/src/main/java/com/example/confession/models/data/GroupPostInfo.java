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

	public GroupPostInfo(ConfessionGroupInfo group, BasicUserInfo author, String content) {

		this(null, group, author, null, content, 0, false);
	}

	public GroupPostInfo(String id,
	                     ConfessionGroupInfo group,
	                     BasicUserInfo author,
	                     BasicUserInfo approver,
	                     String content,
	                     int reaction_count,
	                     boolean react) {

		this.id = id;
		this.group = group;
		this.time_created = Calendar.getInstance().getTime();       // Todo Temp
		this.author = author;
		this.approver = approver;
		this.content = content;
		this.reaction_count = reaction_count;
		this.react = react;
	}
}
