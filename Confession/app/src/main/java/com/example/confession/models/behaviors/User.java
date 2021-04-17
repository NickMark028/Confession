package com.example.confession.models.behaviors;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.confession.models.api.ApiGet;
import com.example.confession.models.api.ApiPost;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class User {

	public final UserInfo user_info;           // Todo temp
	private static String auth_token = null;
	private static User instance = null;

	private User(UserInfo user_info) {

		this.user_info = user_info;
	}

	public synchronized static User GetInstance() {

		return instance;
	}

	public static String GetAuthToken() {

		return auth_token;
	}

	public BasicUserInfo GetBasicUserInfo() {

		return user_info.basic_info;
	}

	public static boolean Register(UserInfo user, String password) {

		HashMap params = new HashMap<String, String>();
		params.put("username", user.basic_info.username);
		params.put("password", password);
		params.put("fullname", user.basic_info.name);
		params.put("email", user.email);
		params.put("phone", user.phone);

		ApiPost ap = new ApiPost("user/register", params);
		Thread t = new Thread(ap);
		t.start();
		while (!ap.isComplete) {
			Log.d("Thread API: ", "Đang đăng ký tài khoản...");
		}

		Log.d("Response", ap.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ap.response);
			if (!obj.has("error")) {
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	public synchronized static User Login(String username, String password) {

		HashMap params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		ApiPost ap = new ApiPost("user/login", params);
		Thread t = new Thread(ap);
		t.start();
		while (!ap.isComplete) {
			Log.d("Thread API: ", "Đang kiểm tra thông tin đăng nhập...");
		}

		Log.d("Response", ap.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ap.response);
			if (!obj.has("error")) {
				String id = obj.getString("_id");
				String name = obj.getString("fullname");
				String token = obj.getString("token");
				String email = obj.getString("email");
				String phone = obj.getString("phone");

				BasicUserInfo basic_info = new BasicUserInfo(id, username, name, "");
				UserInfo info = new UserInfo(basic_info, email, phone, token);
				instance = new User(info);
				auth_token = token;

				return instance;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String GetID()
	{
		return user_info.basic_info.id;
	}

	public ConfessionGroup CreateGroup(ConfessionGroupInfo group) {

		HashMap params = new HashMap<String, String>();
		params.put("token", User.GetAuthToken());
		params.put("shortname", group.short_name);
		params.put("groupname", group.name);
		params.put("avatar", group.avatar);

		ApiPost ap = new ApiPost("confession/new", params);
		Thread t = new Thread(ap);
		t.start();
		while (!ap.isComplete) {
			Log.d("Thread API: ", "Đang tạo confession...");
		}

		ConfessionGroup confession = null;
		Log.d("Response", ap.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ap.response);
			if (!obj.has("error")) {
				String id = obj.getString("_id");
				String shortname = obj.getString("shortname");
				String groupname = obj.getString("groupname");
				String avatar = obj.getString("avatar");
				ConfessionGroupInfo confession_info = new ConfessionGroupInfo(id, shortname, groupname, avatar);
				confession = new ConfessionGroup(confession_info);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return confession;
	}

	public boolean JoinGroup(String group_id) {
		HashMap params = new HashMap<String, String>();
		params.put("token", User.GetAuthToken());
		params.put("confession", group_id);

		ApiPost ap = new ApiPost("confession/join", params);
		Thread t = new Thread(ap);
		t.start();
		while (!ap.isComplete) {
			Log.d("Thread API: ", "Đang tham gia confession...");
		}

		Log.d("Response", ap.response);
		try {
			JSONObject obj = new JSONObject(ap.response);
			if (!obj.has("error")) {
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean LeaveGroup(String group_id) {
		return false;
	}

	public ArrayList<ConfessionGroupInfo> GetFollowedGroups() {

		ArrayList<ConfessionGroupInfo> groups = new ArrayList<>();
		HashMap params = new HashMap<String, String>();
		params.put("token", User.GetAuthToken());
		ApiGet ag = new ApiGet("user/joinedconf", params);

		Thread t = new Thread(ag);
		t.start();
		while (!ag.isComplete) {
			Log.d("Thread API: ", "Đang lấy danh sách các confession đã tham gia...");
		}

		Log.d("Response", ag.response);

		try {
			JSONObject obj = new JSONObject(ag.response);
			if (!obj.has("error")) {
				JSONArray items = new JSONArray(ag.response);
				for (int i = 0; i < items.length(); i++) {
					JSONObject item = items.getJSONObject(i);
					String id = item.getString("_id");
					String shortname = item.getString("shortname");
					String groupname = item.getString("groupname");
					String avatar = item.getString("avatar");
					ConfessionGroupInfo group_info = new ConfessionGroupInfo(id, shortname, groupname, avatar);
					groups.add(group_info);
				}
				return groups;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Phong them jum ham nay nha
	public ArrayList<ConfessionGroupInfo> GetCreatedGroups()
	{
		return null;
	}

	public ArrayList<GroupPostInfo> GetNewsfeed() {

		return null;
	}

	public ArrayList<GroupPostInfo> GetPastPosts() {

		return null;
	}

	public boolean IsAdmin(String group_id) {

		return false;
	}

	@Override
	public String toString() {
		return "User{" +
				"user_info=" + user_info +
				'}';
	}
}
