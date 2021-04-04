package com.example.confession.models.data;

public final class ConfessionGroupInfo {

	public final String id;
	public final String shortname;
	public final String name;
	public final String avatar;

	public ConfessionGroupInfo(String id, String shortname, String name, String avatar) {

		this.shortname = shortname;
		this.id = id;
		this.name = name;
		this.avatar = avatar;
	}
}
