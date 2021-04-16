package com.example.confession.models.data;

import java.io.Serializable;

public final class ConfessionGroupInfo implements Serializable {

	public final String id;
	public final String short_name;
	public final String name;
	public final String avatar;
	public int members = 110;

	public ConfessionGroupInfo(String short_name, String name) {

		this("", short_name, name, "");
	}

	public ConfessionGroupInfo(String short_name, String name, String avatar) {

		this("", short_name, name, avatar);
	}

	public ConfessionGroupInfo(String id, String short_name, String name, String avatar) {

		this.short_name = short_name;
		this.id = id;
		this.name = name;
		this.avatar = avatar;
	}

	public int getMembers() {
		return members;
	}

	@Override
	public String toString() {
		return "ConfessionGroupInfo{" +
				"id='" + id + '\'' +
				", short_name='" + short_name + '\'' +
				", name='" + name + '\'' +
				", avatar='" + avatar + '\'' +
				", members=" + members +
				'}';
	}
}
