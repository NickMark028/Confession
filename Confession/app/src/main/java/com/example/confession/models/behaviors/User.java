package com.example.confession.models.behaviors;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.example.confession.models.api.ApiController;
import com.example.confession.models.api.ApiGet;
import com.example.confession.models.api.ApiPost;
import com.example.confession.models.api.ApiService;
import com.example.confession.models.api.VolleyCallback;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class User{
	protected final UserInfo user_info;

	private User(UserInfo user_info)
	{
		this.user_info = user_info;
	}

	private static boolean Register(UserInfo user, String password, Context context)
	{
		HashMap params = new HashMap<String, String>();
		params.put("username",user.basic_info.username);
		params.put("password",password);
		params.put("fullname",user.basic_info.name);
		params.put("email",user.email);
		params.put("phone",user.phone);

		ApiPost ap = new ApiPost("user/register",params);
		Thread t = new Thread(ap);
		t.start();
		while(!ap.isComplete)
		{
			Log.d("Thread API: ","Đang đăng ký tài khoản...");
		}

		Log.d("Response",ap.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ap.response);
			if(!obj.has("error"))
			{
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static User Login(String username, String password){
		HashMap params = new HashMap<String, String>();
		params.put("username",username);
		params.put("password",password);
		ApiPost ap = new ApiPost("user/login",params);
		Thread t = new Thread(ap);
		t.start();
		while(!ap.isComplete)
		{
			Log.d("Thread API: ","Đang kiểm tra thông tin đăng nhập...");
		}

		Log.d("Response",ap.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ap.response);
			if(!obj.has("error"))
			{
				String id = obj.getString("_id");
				String name = obj.getString("fullname");
				String token = obj.getString("token");
				String email = obj.getString("email");
				String phone = obj.getString("phone");
				BasicUserInfo basic_info = new BasicUserInfo(username, name, "");
				UserInfo info = new UserInfo(basic_info, email, phone,token);
				User user = new User(info);
				return user;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean ViewProfile(Context context)
	{

		return false;
	}

	public ConfessionGroup CreateGroup(ConfessionGroupInfo group)
	{
		HashMap params = new HashMap<String,String>();
		params.put("token",user_info.auth_token);
		params.put("shortname",group.shortname);
		params.put("groupname",group.name);
		params.put("avatar",group.avatar);

		ApiPost ap = new ApiPost("confession/new",params);
		Thread t = new Thread(ap);
		t.start();
		while(!ap.isComplete)
		{
			Log.d("Thread API: ","Đang tạo confession...");
		}

		ConfessionGroup confession  = null;
		Log.d("Response",ap.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ap.response);
			if(!obj.has("error"))
			{
				String id = obj.getString("_id");
				String shortname = obj.getString("shortname");
				String groupname = obj.getString("groupname");
				String avatar = obj.getString("avatar");
				ConfessionGroupInfo confession_info = new ConfessionGroupInfo(id,shortname,groupname,avatar);
				confession = new ConfessionGroup(confession_info);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return confession;
	}

	public boolean JoinGroup(ConfessionGroupInfo group)
	{
		HashMap params = new HashMap<String,String>();
		params.put("token",user_info.auth_token);
		params.put("confession",group.id);

		ApiPost ap = new ApiPost("confession/join",params);
		Thread t = new Thread(ap);
		t.start();
		while(!ap.isComplete)
		{
			Log.d("Thread API: ","Đang tham gia confession...");
		}

		Log.d("Response",ap.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ap.response);
			if(!obj.has("error"))
			{
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean LeaveGroup(ConfessionGroup group)
	{
		return false;
	}

	public ArrayList<ConfessionGroup> GetFollowedGroups()
	{
		ArrayList<ConfessionGroup> groups = new ArrayList<ConfessionGroup>();
		HashMap params = new HashMap<String, String>();
		params.put("token",this.user_info.auth_token);
		ApiGet ag = new ApiGet("user/joinedconf",params);

		Thread t = new Thread(ag);
		t.start();
		while(!ag.isComplete)
		{
			Log.d("Thread API: ","Đang lấy danh sách các confession đã tham gia...");
		}

		Log.d("Response",ag.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ag.response);
			if(!obj.has("error"))
			{
				JSONArray items = new JSONArray(ag.response);
				for(int i=0;i<items.length();i++)
				{
					JSONObject item = items.getJSONObject(i);
					String id = item.getString("_id");
					String shortname = item.getString("shortname");
					String groupname = item.getString("groupname");
					String avatar = item.getString("avatar");
					ConfessionGroupInfo group_info = new ConfessionGroupInfo(id,shortname,groupname,avatar);
					groups.add(new ConfessionGroup(group_info));
				}
				return groups;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
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