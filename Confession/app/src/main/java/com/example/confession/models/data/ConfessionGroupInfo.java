package com.example.confession.models.data;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

public final class ConfessionGroupInfo implements Serializable {

	public final String id;
	public final String short_name;     // Ignore
	public final String name;
	public final Object avatar;
	public int member_count;

	public ConfessionGroupInfo(String name) {
		this(name, name);
	}

	public ConfessionGroupInfo(String short_name, String name) {
		this(short_name, name, null);
	}

	public ConfessionGroupInfo(String short_name, String name, Object avatar) {
		this(null, short_name, name, avatar, 0);
	}

	public ConfessionGroupInfo(String id,
	                           String short_name,
	                           String name,
	                           Object avatar,
	                           int member_count) {

		this.id = id;
		this.short_name = short_name;
		this.name = name;
		this.avatar = avatar;
		this.member_count = member_count;
	}

	public void AddDataTo(Intent intent) {
		intent.putExtra("cgi", this);
	}

	public static ConfessionGroupInfo From(Intent intent) {
		return (ConfessionGroupInfo) intent.getSerializableExtra("cgi");
	}
}
