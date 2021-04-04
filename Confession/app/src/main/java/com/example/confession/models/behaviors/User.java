package com.example.confession.models.behaviors;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.confession.models.api.ApiService;
import com.example.confession.models.api.VolleyCallback;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {

	protected final UserInfo user_info;

	private User(UserInfo user_info)
	{
		this.user_info = user_info;
	}

	private static boolean Register(UserInfo user_info, String password, Context context)
	{
		final ApiService AS = new ApiService(context,"user/register");
		AS.addHeader("username",user_info.basic_info.username);
		AS.addHeader("password",password);
		AS.addHeader("fullname",user_info.basic_info.name);
		AS.addHeader("email",user_info.email);
		AS.addHeader("phone",user_info.phone);
		AS.executeRequest(Request.Method.POST, new VolleyCallback() {
			@Override
			public void getResponse(String response) throws JSONException {
				JSONObject obj = new JSONObject(response);
				if(!obj.has("error"))
				{
					Log.d("Register: ",obj.getString("username"));
				}
				else
				{
					String error = obj.getString("error");
					Log.d("Error: ",error);
				}
			}
		});
		return false;
	}

	public static User Login(String username, String password, Context context)
	{
		/*if (username.equals("admin") && password.equals("123456"))
		{
			BasicUserInfo basic_info = new BasicUserInfo("admin", "K", "");
			UserInfo info = new UserInfo(basic_info, "test@gmail.com", "09xx xxx xx1");
			return new User(info);
		}
		else
		{
			return null;
		}*/
		Log.d("Log","OK");
		final ApiService AS = new ApiService(context,"user/login");
		AS.addHeader("username",username);
		AS.addHeader("password",password);
		AS.executeRequest(Request.Method.POST, new VolleyCallback() {
			@Override
			public void getResponse(String response) throws JSONException {
				JSONObject obj = new JSONObject(response);
				if(!obj.has("error"))
				{
					String username = obj.getString("username");
					String name = obj.getString("fullname");
					String token = obj.getString("token");
					String email = obj.getString("email");
					String phone = obj.getString("phone");
					BasicUserInfo basic_info = new BasicUserInfo(username, name, "");
					UserInfo info = new UserInfo(basic_info, email, phone,token);
					new User(info);
					Log.d("Username: ",username);
				}
				else
				{
					String error = obj.getString("error");
					Log.d("Error: ",error);
				}
			}
		});
		return null;
	}

	public boolean ViewProfile()
	{

		return false;
	}

	public ConfessionGroup CreateGroup(ConfessionGroupInfo group, Context context)
	{
		final ApiService AS = new ApiService(context,"confession/new");
		AS.addHeader("token",user_info.auth_token);
		AS.addHeader("shortname",group.name); // Thêm thuộc tính shortname cho confession.
		AS.addHeader("groupname",group.name);
		AS.addHeader("avatar",group.avatar);

		AS.executeRequest(Request.Method.POST, new VolleyCallback() {
			@Override
			public void getResponse(String response) throws JSONException {
				JSONObject obj = new JSONObject(response);
				if(!obj.has("error"))
				{
					Log.d("Create Confession: ",obj.getString("groupname"));
				}
				else
				{
					String error = obj.getString("error");
					Log.d("Error: ",error);
				}
			}
		});

		return null;
	}

	public boolean JoinGroup(ConfessionGroupInfo group, Context context)
	{
		final ApiService AS = new ApiService(context,"confession/join");
		AS.addHeader("token",user_info.auth_token);
		AS.addHeader("confession",group.id);
		AS.executeRequest(Request.Method.POST, new VolleyCallback() {
			@Override
			public void getResponse(String response) throws JSONException {
				JSONObject obj = new JSONObject(response);
				if(!obj.has("error"))
				{
					Log.d("Join Confession: ",".");
				}
				else
				{
					String error = obj.getString("error");
					Log.d("Error: ",error);
				}
			}
		});
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
