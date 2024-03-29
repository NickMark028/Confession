package com.example.confession.models.behaviors;

import android.util.Log;

import com.example.confession.models.api.ApiGet;
import com.example.confession.models.api.ApiPost;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.UserInfo;
import com.example.confession.models.data.UserState;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class User {

	public final UserInfo user_info;
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

	public UserInfo GetUserInfo() {
		return user_info;
	}

	public BasicUserInfo GetBasicUserInfo() {
		return user_info.basic_info;
	}

	// Done //
	public static boolean Register(UserInfo user, String password) {

		HashMap params = new HashMap<String, String>();
		params.put("username", user.basic_info.username);
		params.put("password", password);
		params.put("fullname", user.basic_info.name);
		params.put("email", user.email);
		params.put("phone", user.phone);

		ApiPost ap = new ApiPost("user/register", params);
		Log.d("Thread API: ", "Đang đăng ký tài khoản...");
		ap.run();

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

	// Cookie //
	public static User checkToken(String token) {

		HashMap params = new HashMap<String, String>();
		params.put("token", token);
		ApiGet ag = new ApiGet("user/islogin/", params);

		Log.d("Thread API: ", "Token check validation...");
		ag.run();
		Log.d("Response", ag.response);

		JSONObject obj = null;
		try {
			obj = new JSONObject(ag.response);
			if (!obj.has("error")) {
				String id = obj.getString("_id");
				String name = obj.getString("fullname");
				String username = obj.getString("username");
				//String token = obj.getString("token");
				String email = obj.getString("email");
				String phone = obj.getString("phone");

				BasicUserInfo basic_info = new BasicUserInfo(id, username, name, "");
				UserInfo info = new UserInfo(basic_info, email, phone);
				instance = new User(info);
				auth_token = token;
				return instance;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}


	// Done //
	public static User Login(String username, String password) {

		HashMap params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		ApiPost ap = new ApiPost("user/login", params);

		Log.d("Thread API: ", "Đang kiểm tra thông tin đăng nhập...");
		ap.run();
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
				UserInfo info = new UserInfo(basic_info, email, phone);
				instance = new User(info);
				auth_token = token;

				return instance;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static User Login(String auth_token) {
		return null;
	}

	public String GetID() {
		return user_info.basic_info.id;
	}

	public ConfessionGroup CreateGroup(ConfessionGroupInfo group) {

		HashMap params = new HashMap<String, String>();
		params.put("token", User.GetAuthToken());
		params.put("shortname", group.short_name);
		params.put("groupname", group.name);
		params.put("avatar", "cat.png");

		ApiPost ap = new ApiPost("confession/new", params);
		Log.d("Thread API: ", "Đang tạo confession...");
		ap.run();

		ConfessionGroup confession = null;
		Log.d("Create Group Response", ap.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ap.response);
			if (!obj.has("error")) {
				String id = obj.getString("_id");
				String shortname = obj.getString("shortname");
				String groupname = obj.getString("groupname");
				String avatar = obj.getString("avatar");

				ConfessionGroupInfo confession_info = new ConfessionGroupInfo(id, shortname, groupname, avatar, 0);
				confession = new ConfessionGroup(confession_info);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return confession;
	}

	// Done //
	public boolean JoinGroup(String group_id) {
		HashMap params = new HashMap<String, String>();
		params.put("token", User.GetAuthToken());
		params.put("confession", group_id);

		ApiPost ap = new ApiPost("confession/join", params);
		Log.d("Thread API: ", "Đang tham gia confession...");
		ap.run();

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

	// TODO
	public boolean LeaveGroup(String group_id) {
		return false;
	}

	// Done //
	public ArrayList<ConfessionGroupInfo> GetFollowedGroups() {

		ArrayList<ConfessionGroupInfo> groups = new ArrayList<>();
		HashMap params = new HashMap<String, String>();
		params.put("token", User.GetAuthToken());

		String url = "user/joinedconfnorole";
		ApiGet ag = new ApiGet(url, params);
		Log.d("Thread API: ", "Đang lấy danh sách các confession đã tham gia...");
		ag.run();

		Log.d("Response", ag.response);

		try {
			JSONArray items = new JSONArray(ag.response);
			if (!items.isNull(0)) {

				for (int i = 0; i < items.length(); i++) {
					JSONObject item = items.getJSONObject(i);
					String id = item.getString("_id");
					String shortname = item.getString("shortname");
					String groupname = item.getString("groupname");
					String avatar = item.getString("avatar");

					JSONArray members = item.getJSONArray("members"); // moi them
					int member_count = members.length(); // moi them

					ConfessionGroupInfo group_info = new ConfessionGroupInfo(id, shortname, groupname, avatar, member_count);
					groups.add(group_info);
				}
				return groups;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Done //
	public ArrayList<ConfessionGroupInfo> GetCreatedGroups() {
		ArrayList<ConfessionGroupInfo> groups = new ArrayList<>();
		HashMap params = new HashMap<String, String>();
		params.put("token", User.GetAuthToken());

		String url = "user/joinedconfrole";
		ApiGet ag = new ApiGet(url, params);

		Log.d("Thread API: ", "Đang lấy danh sách các confession đang quản lý...");
		ag.run();

		Log.d("Response", ag.response);

		try {
			JSONArray items = new JSONArray(ag.response);
			if (!items.isNull(0)) {

				for (int i = 0; i < items.length(); i++) {
					JSONObject item = items.getJSONObject(i);
					String id = item.getString("_id");
					String shortname = item.getString("shortname");
					String groupname = item.getString("groupname");
					String avatar = item.getString("avatar");

					JSONArray members = item.getJSONArray("members"); // moi them
					int member_count = members.length(); // moi them

					ConfessionGroupInfo group_info = new ConfessionGroupInfo(id, shortname, groupname, avatar, member_count);
					groups.add(group_info);
				}
				return groups;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Done //
	public ArrayList<ConfessionGroupInfo> GetJoinedGroups() {
		ArrayList<ConfessionGroupInfo> joined_groups = GetFollowedGroups();
		ArrayList<ConfessionGroupInfo> created_groups = GetCreatedGroups();

		if (joined_groups == null && created_groups == null)
			return null;

		if (joined_groups == null) {
			joined_groups = new ArrayList<>();
		}

		try {
			joined_groups.addAll(created_groups);
		} catch (NullPointerException exc) {
			Log.e("NullExc", exc.getMessage());
		}
		return joined_groups;
	}

	// Done //
	public ArrayList<GroupPostInfo> GetNewsfeed() {
		ArrayList<GroupPostInfo> posts = new ArrayList<GroupPostInfo>();
		HashMap params = new HashMap<String, String>();
		params.put("token", User.GetAuthToken());

		String url = "user/newsfeed";
		ApiGet ag = new ApiGet(url, params);
		Log.d("Thread API: ", "Đang lấy Newsfeed...");
		ag.run();

		Log.d("Response", ag.response);

		try {
			JSONArray items = new JSONArray(ag.response);
			if (!items.isNull(0)) {

				for (int i = 0; i < items.length(); i++) {
					JSONObject item = items.getJSONObject(i);

					String groupid = item.getString("groupid");
					String shortname = item.getString("shortname");
					String groupname = item.getString("groupname");
					String avatar = item.getString("avatar");
					Boolean isreact = item.getBoolean("isreact");

					String id = item.getString("_id");
					String content = item.getString("content");
					int createAt = item.getInt("createAt");
					// TODO thay thanh gia tri hop le
					BasicUserInfo author = new BasicUserInfo("", "", "", "");
					BasicUserInfo approver = new BasicUserInfo("", "", "", ""); // khong co luu thong tin nay tren DB.
					ConfessionGroupInfo group = new ConfessionGroupInfo(groupid, shortname, groupname, avatar, 0);
					JSONArray reactions = item.getJSONArray("reactions");
					int reaction_count = reactions.length();

					GroupPostInfo post = new GroupPostInfo(id, group, author, approver, content, reaction_count, isreact);
					posts.add(post);
				}
				return posts;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<GroupPostInfo> GetPastPosts() {

		return null;
	}

	public UserState GetState(String group_id) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", auth_token);
		params.put("groupid", group_id);
		ApiGet ag = new ApiGet("user/states/", params);
		ag.run();
		Log.d("State: ", ag.response);

		try {
			JSONObject obj = new JSONObject(ag.response);
			boolean isAdmin = obj.getBoolean("isAdmin");
			boolean isMember = obj.getBoolean("isMember");
			boolean isPending = obj.getBoolean("isPending");
			UserState userState;
			if (isAdmin) userState = UserState.Admin;
			else if (isMember) userState = UserState.Following;
			else if (isPending) userState = UserState.Pending;
			else userState = UserState.NonMember;
			Log.e("State: ", userState.toString());
			return userState;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return UserState.Undefined;
	}

	public boolean RemoveGroup(String group_id) {
		return false;
	}

	public User UpdatePassword(String old_pass, String new_pass) {
		User updated_user = null;

		String auth_token = instance.auth_token;
		String fullname = instance.user_info.basic_info.name;

		HashMap params = new HashMap<String, String>();
		params.put("token", auth_token);
		params.put("fullname", fullname);
		params.put("password", new_pass);
		ApiPost ap = new ApiPost("user/profile", params);

		Log.d("Thread API: ", "Đang cập nhật thông tin cá nhân...");
		ap.run();
		Log.d("Response", ap.response);

		JSONObject obj = null;
		try {
			obj = new JSONObject(ap.response);
			if (!obj.has("error")) {
				BasicUserInfo basicUserInfo = new BasicUserInfo(instance.user_info.basic_info.id, instance.user_info.basic_info.username, fullname, instance.user_info.basic_info.avatar);
				UserInfo userInfo = new UserInfo(basicUserInfo, instance.user_info.email, instance.user_info.phone);
				updated_user = new User(userInfo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Update singleton
		if (updated_user != null)
			instance = updated_user;

		return updated_user;
	}

	// DONE
	public User UpdateUserInfo(UserInfo user_info) {
		User updated_user = null;

		String auth_token = instance.auth_token;

		HashMap params = new HashMap<String, String>();
		params.put("token", auth_token);
		params.put("fullname", user_info.basic_info.name);
		params.put("email", user_info.email);
		params.put("phone", user_info.phone);
		ApiPost ap = new ApiPost("user/profile3", params);

		Log.d("Thread API: ", "Đang cập nhật thông tin cá nhân...");
		ap.run();
		Log.d("Response", ap.response);

		JSONObject obj = null;
		try {
			obj = new JSONObject(ap.response);
			if (!obj.has("error")) {
				BasicUserInfo basicUserInfo = new BasicUserInfo(user_info.basic_info.id, user_info.basic_info.username, user_info.basic_info.name, user_info.basic_info.avatar);
				UserInfo userInfo = new UserInfo(basicUserInfo, user_info.email, user_info.phone);
				updated_user = new User(userInfo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Update singleton
		if (updated_user != null)
			instance = updated_user;

		return updated_user;
	}

	@Override
	public String toString() {
		return "User{" +
				"user_info=" + user_info +
				'}';
	}
}
