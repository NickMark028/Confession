package com.example.confession.models.data;

import java.io.Serializable;

public final class BasicUserInfo implements Serializable {

	public final String id;
	public final String username;
	public final String name;
	public final String avatar;

	public BasicUserInfo(String username, String name) {
		this("", username, name, "");
	}

	public BasicUserInfo(String username, String name, String avatar) {
		this("", username, name, avatar);
	}

	public BasicUserInfo(String id, String username, String name, String avatar) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.avatar = avatar;
	}
}
