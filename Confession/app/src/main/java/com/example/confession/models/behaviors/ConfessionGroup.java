package com.example.confession.models.behaviors;

import android.content.Context;
import android.util.JsonWriter;
import android.util.Log;

import com.android.volley.Request;
import com.example.confession.models.api.ApiService;
import com.example.confession.models.api.VolleyCallback;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConfessionGroup {

	protected final ConfessionGroupInfo group_info;
	protected ArrayList<GroupPost> posts;
	protected ArrayList<BasicUserInfo> members;

	public ConfessionGroup(ConfessionGroupInfo group_info) {
		this.group_info = group_info;
	}

	public boolean getPosts(Context context)
	{
		final ApiService AS = new ApiService(context,"confession/id?conf="+group_info.id);
		AS.executeRequest(Request.Method.GET, new VolleyCallback() {
			@Override
			public void getResponse(String response) throws JSONException {
				JSONObject obj = new JSONObject(response);
				if(!obj.has("error"))
				{
					JSONArray arr = obj.getJSONArray("posts");
					for(int i=0;i<arr.length();i++)
					{
						JSONObject post = arr.getJSONObject(i);

					}
					Log.d("Get posts: ",".");
				}
				else
				{
					String error = obj.getString("error");
					Log.d("Error: ",error);
				}
			}
		});
		return true;
	}

	public boolean getMembers(Context context)
	{
//		final ApiService AS = new ApiService(context,"confession/id?conf="+group_info.id);
//		AS.executeRequest(Request.Method.GET, new VolleyCallback() {
//			@Override
//			public void getResponse(String response) throws JSONException {
//				JSONObject obj = new JSONObject(response);
//				if(!obj.has("error"))
//				{
//					JSONArray arr = obj.getJSONArray("members");
//					for(int i=0;i<arr.length();i++)
//					{
//						JSONObject member = arr.getJSONObject(i);
//						String username = member.getString("username");
//						String fullname = member.getString("fullname");
//						String avatar = member.getString("avatar"); // Xem lại tên thuộc tính.
//						BasicUserInfo meminfo = new BasicUserInfo(username,fullname,avatar);
//						members.add(meminfo);
//					}
//					Log.d("Get members: ",".");
//				}
//				else
//				{
//					String error = obj.getString("error");
//					Log.d("Error: ",error);
//				}
//			}
//		});
		return false;
	}

	public static ArrayList<ConfessionGroupInfo> Find(String name, Context context)
	{
		final ApiService AS = new ApiService(context, "confession/search/?keyword=" + name);
		AS.executeRequest(Request.Method.GET, new VolleyCallback() {
			@Override
			public void getResponse(String response) throws JSONException {
				//JSONObject obj = new JSONObject(response);
//				if(!obj.has("error"))
//				{
//					Log.d("Find: ", response);
//				}
//				else
//				{
//					String error = obj.getString("error");
//					Log.d("Error: ",error);
//				}
				Log.d("Find", response);
			}
		});

		return null;
	}
}
