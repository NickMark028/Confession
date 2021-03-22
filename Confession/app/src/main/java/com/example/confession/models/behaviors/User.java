package com.example.confession.models.behaviors;

import android.util.Log;

import com.example.confession.models.data.BasicUserInfo;
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
		// Testing
		if (username.equals("admin") && password.equals("123456"))
		{
			BasicUserInfo basic_info = new BasicUserInfo("admin", "K", "");
			UserInfo info = new UserInfo(basic_info, "test@gmail.com", "09xx xxx xx1");
			return new User(info);
		}
		else
		{
			return null;
		}
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
