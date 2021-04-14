package com.example.confession.models.behaviors;

import android.content.Context;
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

	public Bundle ToBundle() {

		Bundle bundle = new Bundle();
		bundle.putSerializable("group_info", this.group_info);
		return bundle;
	}

	public static ConfessionGroup From(Bundle bundle) {

		ConfessionGroupInfo info = (ConfessionGroupInfo) bundle.getSerializable("group_info");
		return new ConfessionGroup(info);
	}

	public ConfessionGroupInfo GetGroupInfo() {
		return group_info;
	}

	public int GetMemberCount()
	{
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
//
	public static ArrayList<ConfessionGroupInfo> Find(String name) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("keyword", name);

		ApiGet ag = new ApiGet("confession/search", params);
		Thread t = new Thread(ag);
		t.start();

		while (!ag.isComplete) {
			Log.d("Thread API: ", "Đang tìm kiếm các nhóm theo từ khóa...");
		}

		ArrayList<ConfessionGroupInfo> groups = new ArrayList<ConfessionGroupInfo>();
		Log.d("Response", ag.response);
		JSONObject obj = null;
		try {
			//obj = new JSONObject(ag.response);
			if (!ag.response.equals("")) {
				JSONArray items = new JSONArray(ag.response);
				for(int i=0;i<items.length();i++)
				{
					JSONObject item = items.getJSONObject(i);
					String id = item.getString("_id");
					String shortname = item.getString("shortname");
					String groupname = item.getString("groupname");
					String avatar = item.getString("avatar");
					ConfessionGroupInfo group_info = new ConfessionGroupInfo(id,shortname,groupname,avatar);
					groups.add(group_info);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return groups;
	}

	public ArrayList<BasicUserInfo> GetPendingUsers(UserInfo admin) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("conf", this.group_info.id);

		ApiGet ag = new ApiGet("confession/id", params);
		Thread t = new Thread(ag);
		t.start();
		while (!ag.isComplete) {
			Log.d("Thread API: ", "Đang lấy danh sách thành viên chờ...");
		}

		ArrayList<BasicUserInfo> users = new ArrayList<BasicUserInfo>();
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
					BasicUserInfo user = new BasicUserInfo(id,username, name, "");
					users.add(user);
				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean AcceptUser(BasicUserInfo user, UserInfo admin) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", admin.auth_token);
		params.put("confession", this.group_info.id);
		params.put("premem",user.id); // Có nguy cơ sai.

		ApiPost ap = new ApiPost("confession/addmember", params);
		Thread t = new Thread(ap);
		t.start();
		while (!ap.isComplete) {
			Log.d("Thread API: ", "Đang chấp nhận thành viên chờ...");
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

	public boolean RejectUser(BasicUserInfo user, UserInfo admin) {
		return false;
	}

	public ArrayList<BasicUserInfo> GetMembers(UserInfo admin) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("conf", this.group_info.id);

		ApiGet ag = new ApiGet("confession/id", params);
		Thread t = new Thread(ag);
		t.start();
		while (!ag.isComplete) {
			Log.d("Thread API: ", "Đang lấy danh sách thành viên...");
		}

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
					BasicUserInfo user = new BasicUserInfo(id,username, name, avatar);
					members.add(user);
				}


			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return members;
	}

	public ArrayList<BasicUserInfo> GetAdmins(UserInfo admin) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("conf", this.group_info.id);

		ApiGet ag = new ApiGet("confession/id", params);
		Thread t = new Thread(ag);
		t.start();
		while (!ag.isComplete) {
			Log.d("Thread API: ", "Đang lấy danh sách thành admin...");
		}

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
					BasicUserInfo user = new BasicUserInfo(id,username, name, avatar);
					admins.add(user);
				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return admins;
	}

	public static ArrayList<ConfessionGroupInfo> GetAll() {
		HashMap<String, String> params = new HashMap<String, String>();
		ApiGet ag = new ApiGet("confession/", params);
		Thread t = new Thread(ag);
		t.start();
		while (!ag.isComplete) {
			Log.d("Thread API: ", "Đang lấy tất cả confession...");
		}

		ArrayList<ConfessionGroupInfo> confessions = new ArrayList<ConfessionGroupInfo>();
		Log.d("Response", ag.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ag.response);
			if (!obj.has("error")) {

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	} // Check lại api không trả ra []

	public GroupPost CreatePost(GroupPostInfo post, UserInfo member) {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", member.auth_token);
		params.put("confessionid", this.group_info.id);
		params.put("memberid",member.basic_info.id);
		params.put("title", post.id);
		params.put("content", post.content);
		ApiPost ap = new ApiPost("post/new", params);
		Thread t = new Thread(ap);
		t.start();
		while (!ap.isComplete) {
			Log.d("Thread API: ", "Đang đăng bài...");
		}

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

	public ArrayList<GroupPost> GetPosts(UserInfo member) // Hoạt động tốt.
	{
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("conf", this.group_info.id);

		ApiGet ag = new ApiGet("confession/id", params);
		Thread t = new Thread(ag);
		t.start();

		while (!ag.isComplete) {
			Log.d("Thread API: ", "Đang lấy danh sách tat ca bai dang...");
		}

		ArrayList<GroupPost> posts = new ArrayList<GroupPost>();
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

					String posterid=poster.getString("_id");
					String username =poster.getString("username");
					String fullname =poster.getString("fullname");
					BasicUserInfo author = new BasicUserInfo(posterid,username,fullname,"");
					BasicUserInfo approver = member.basic_info;
					String content = item.getString("content");

					GroupPostInfo post_info = new GroupPostInfo(id,author,approver,content);
					GroupPost post = new GroupPost(post_info,this.group_info);
					posts.add(post);
				}
				return posts;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return "ConfessionGroup{" +
				"group_info=" + group_info +
				'}';
	}
}