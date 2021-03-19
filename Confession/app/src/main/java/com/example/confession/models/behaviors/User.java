package com.example.confession.models.behaviors;

import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.UserInfo;

import java.util.ArrayList;

public class User {

	protected final UserInfo user_info;

	private User(UserInfo user_info) {
		this.user_info = user_info;
	}

	private static boolean Register(UserInfo user_info, String password)
	{
		return false;
	}

	public static User Login(String username, String password)
	{
		return null;
	}

	public boolean ViewProfile()
	{
		return false;
	}

	public ConfessionGroup CreateGroup(ConfessionGroupInfo group)
	{
		return null;
	}

	public boolean JoinGroup(ConfessionGroupInfo group)
	{
		return false;
	}

	public boolean LeaveGroup(ConfessionGroup group)
	{
		return false;
	}

	public ArrayList<ConfessionGroup> GetFollowedGroups()
	{
		return null;
	}

	public ArrayList<GroupPost> GetNewsfeed()
	{
		return null;
	}

	public ArrayList<GroupPost> GetPastPosts()
	{
		return null;
	}

	public boolean IsAdmin(ConfessionGroup group)
	{
		return false;
	}
}
