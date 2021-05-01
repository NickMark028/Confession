package com.example.confession.models.behaviors;

import android.content.Context;
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

public class ConfessionGroup {

	protected final ConfessionGroupInfo group_info;

	protected ArrayList<GroupPost> posts;
	protected ArrayList<BasicUserInfo> members;

	public ConfessionGroup(ConfessionGroupInfo group_info) {

		this.group_info = group_info;
	}

	public void AddTo(Intent intent)
	{
		intent.putExtra("group_info", group_info);
	}

	public static ConfessionGroup From(Bundle bundle) {

		ConfessionGroupInfo info = (ConfessionGroupInfo) bundle.getSerializable("group_info");
		return new ConfessionGroup(info);
	}

	public String GetID() {

		return group_info.id;
	}

	// Done //
	public ArrayList<BasicUserInfo> GetPendingUsers(String auth_token) {

		HashMap<String, String> params = new HashMap<>();
		params.put("conf", this.group_info.id);

		ApiGet ag = new ApiGet("confession/id", params);

		Log.d("Thread API: ", "Đang lấy danh sách thành viên chờ...");
		ag.run();

		ArrayList<BasicUserInfo> users = new ArrayList<>();
		Log.d("Response", ag.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ag.response);
			if (!obj.has("error")) {
				JSONArray items = obj.getJSONArray("users");

				for (int i = 0; i < items.length(); i++) {
					JSONObject item = items.getJSONObject(i);
					String id = item.getString("_id");
					String username = item.getString("username");
					String name = item.getString("fullname");
					String avatar = "";
					BasicUserInfo user = new BasicUserInfo(id, username, name, "");
					users.add(user);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return users;
	}

	// Done //
	public boolean AcceptUser(String user_id, String auth_token) {

		HashMap<String, String> params = new HashMap<>();
		params.put("token", User.GetAuthToken());
		params.put("confession", this.group_info.id);
		params.put("premem", user_id); // Có nguy cơ sai.

		ApiPost ap = new ApiPost("confession/addmember", params);

		Log.d("Thread API: ", "Đang chấp nhận thành viên chờ...");
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

	// Write API later //
	public boolean RejectUser(String user_id, String auth_token) {
		return false;
	}

	@Deprecated
	public ConfessionGroupInfo GetGroupInfo() {
		return group_info;
	}

	// Todo change for later
	public int GetMemberCount() {
		return members.size();
	}
	//	public boolean getPosts(Context context)
//	{
//		final ApiService AS = new ApiService(context,"confession/id?conf="+group_info.id);
//		AS.executeRequest(Request.Method.GET, new VolleyCallback() {
//			@Override
//			public void getResponse(String response) throws JSONException {
//				JSONObject obj = new JSONObject(response);
//				if(!obj.has("error"))
//				{
//					JSONArray arr = obj.getJSONArray("posts");
//					for(int i=0;i<arr.length();i++)
//					{
//						JSONObject post = arr.getJSONObject(i);
//
//					}
//					Log.d("Get posts: ",".");
//				}
//				else
//				{
//					String error = obj.getString("error");
//					Log.d("Error: ",error);
//				}
//			}
//		});
//		return true;
//	}
//
//	public boolean getMembers(Context context)
//	{
////		final ApiService AS = new ApiService(context,"confession/id?conf="+group_info.id);
////		AS.executeRequest(Request.Method.GET, new VolleyCallback() {
////			@Override
////			public void getResponse(String response) throws JSONException {
////				JSONObject obj = new JSONObject(response);
////				if(!obj.has("error"))
////				{
////					JSONArray arr = obj.getJSONArray("members");
////					for(int i=0;i<arr.length();i++)
////					{
////						JSONObject member = arr.getJSONObject(i);
////						String username = member.getString("username");
////						String fullname = member.getString("fullname");
////						String avatar = member.getString("avatar"); // Xem lại tên thuộc tính.
////						BasicUserInfo meminfo = new BasicUserInfo(username,fullname,avatar);
////						members.add(meminfo);
////					}
////					Log.d("Get members: ",".");
////				}
////				else
////				{
////					String error = obj.getString("error");
////					Log.d("Error: ",error);
////				}
////			}
////		});
//		return false;
//	}

	// -Done- //
	public ArrayList<BasicUserInfo> GetMembers(String auth_token) {

	HashMap<String, String> params = new HashMap<String, String>();
	params.put("conf", this.group_info.id);

	ApiGet ag = new ApiGet("confession/id", params);


	Log.d("Thread API: ", "Đang lấy danh sách thành viên...");
	ag.run();

	ArrayList<BasicUserInfo> members = new ArrayList<BasicUserInfo>();
	Log.d("Response", ag.response);
	JSONObject obj = null;
	try {
		obj = new JSONObject(ag.response);
		if (!obj.has("error")) {
			JSONArray items = obj.getJSONArray("members");

			for (int i = 0; i < items.length(); i++) {

				JSONObject item = items.getJSONObject(i);
				JSONObject subitem = item.getJSONObject("userid");

				String id = subitem.getString("_id");
				String username = subitem.getString("username");
				String name = subitem.getString("fullname");
				String avatar = "";
				BasicUserInfo user = new BasicUserInfo(id, username, name, avatar);
				members.add(user);
			}

		}
	} catch (JSONException e) {
		e.printStackTrace();
	}
	return members;
}

	//- Done- //
	public ArrayList<BasicUserInfo> GetAdmins(String auth_token) {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("conf", this.group_info.id);

		ApiGet ag = new ApiGet("confession/id", params);
		Log.d("Thread API: ", "Đang lấy danh sách thành admin...");
		ag.run();

		ArrayList<BasicUserInfo> admins = new ArrayList<BasicUserInfo>();
		Log.d("Response", ag.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ag.response);
			if (!obj.has("error")) {
				JSONArray items = obj.getJSONArray("admins");

				for (int i = 0; i < items.length(); i++) {
					JSONObject item = items.getJSONObject(i);
					JSONObject subitem = item.getJSONObject("memberid");
					JSONObject subsubitem = subitem.getJSONObject("userid");

					String id = subsubitem.getString("_id");
					String username = subsubitem.getString("username");
					String name = subsubitem.getString("fullname");
					String avatar = "";
					BasicUserInfo user = new BasicUserInfo(id, username, name, avatar);
					admins.add(user);
				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return admins;
	}

// Testing
//	public static ArrayList<ConfessionGroupInfo> GetAll() {
//
//		HashMap<String, String> params = new HashMap<>();
//		ApiGet ag = new ApiGet("confession/", params);
//		Thread t = new Thread(ag);
//		t.start();
//		while (!ag.isComplete) {
//			Log.d("Thread API: ", "Đang lấy tất cả confession...");
//		}
//
//		ArrayList<ConfessionGroupInfo> confessions = new ArrayList<ConfessionGroupInfo>();
//		Log.d("Response", ag.response);
//		JSONObject obj = null;
//		try {
//			obj = new JSONObject(ag.response);
//			if (!obj.has("error")) {
//
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return null;
//	} // Check lại api không trả ra []

	// Done //
	public static ArrayList<ConfessionGroupInfo> Find(String name) {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("keyword", name);
		if(name.equals("")) return null;
		ApiGet ag = new ApiGet("confession/search", params);


		Log.d("Thread API: ", "Đang tìm kiếm các nhóm theo từ khóa...");
		ag.run();

		ArrayList<ConfessionGroupInfo> groups = new ArrayList<ConfessionGroupInfo>();
		Log.d("Response", ag.response);
		JSONObject obj = null;
		try {
			//obj = new JSONObject(ag.response);
			if (!ag.response.equals("")) {
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
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return groups;
	}

	// Done //
	public GroupPost AddPost(GroupPostInfo post, BasicUserInfo member, String auth_token) {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", User.GetAuthToken());
		params.put("confessionid", this.group_info.id);
		params.put("memberid", member.id);
		params.put("title", post.id);
		params.put("content", post.content);
		ApiPost ap = new ApiPost("post/new", params);

		Log.d("Thread API: ", "Đang đăng bài...");
		ap.run();

		ArrayList<ConfessionGroupInfo> confessions = new ArrayList<ConfessionGroupInfo>();
		Log.d("Response", ap.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ap.response);
			if (!obj.has("error")) {
				GroupPost groupPost = new GroupPost(post, this.group_info);
				return groupPost;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	// -Done- //
	public ArrayList<GroupPostInfo> GetPosts(String auth_token) // Hoạt động tốt.
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("conf", this.group_info.id);

		ApiGet ag = new ApiGet("confession/id", params);
		Thread t = new Thread(ag);
		t.start();

		Log.d("Thread API: ", "Đang lấy danh sách tat ca bai dang...");
		while (!ag.isComplete);

		ArrayList<GroupPostInfo> posts = new ArrayList<>();
		Log.e("Response", ag.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ag.response);
			if (!obj.has("error")) {
				JSONArray items = obj.getJSONArray("posts");
				for (int i = 0; i < items.length(); i++) {
					JSONObject item = items.getJSONObject(i);
					String id = item.getString("_id");

					JSONObject poster = item.getJSONObject("memberid");
					poster = poster.getJSONObject("userid");

					String posterid = poster.getString("_id");
					String username = poster.getString("username");
					String fullname = poster.getString("fullname");
					BasicUserInfo author = new BasicUserInfo(posterid, username, fullname, "");

					// TODO: Change approver to the one who approved the post
					BasicUserInfo approver = new BasicUserInfo("Add approver username here", "Add approver name here") ;

					String content = item.getString("content");

					GroupPostInfo post_info = new GroupPostInfo(id, author, approver, content);
					posts.add(post_info);
				}
				return posts;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	// -Writr API later- //
	public ArrayList<GroupPostInfo> GetPendingPosts(String auth_token)
	{
		return null;
	}

	// Writr API later //
	public boolean AcceptPost(String post_id, String auth_token)
	{
		return false;
	}

	// Writr API later //
	public boolean RejectPost(String post_id, String auth_token)
	{
		return false;
	}

	// Writr API later //
	public boolean PinPostToTop(String post_id, String auth_token)
	{
		return false;
	}

	// Writr API later //
	public boolean RemovePost(String post_id, String auth_token)
	{
		return false;
	}

	@Override
	public String toString() {
		return "ConfessionGroup{" +
				"group_info=" + group_info +
				'}';
	}
}
