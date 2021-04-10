package com.example.confession.models.behaviors;

import android.util.Log;

import com.example.confession.models.api.ApiGet;
import com.example.confession.models.api.ApiPost;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;
import com.example.confession.models.data.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupPost {

	protected final GroupPostInfo post_info;
	protected final ConfessionGroupInfo group;
	protected ArrayList<PostComment> comments;

	public GroupPost(GroupPostInfo post_info, ConfessionGroupInfo group) {
		this.post_info = post_info;
		this.group = group;
	}

	public PostComment AddComment(PostCommentInfo comment, UserInfo member)
	{
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", member.auth_token);
		params.put("memberid", member.basic_info.id);
		params.put("postid", comment.id);
		params.put("content", comment.content);

		ApiPost ap = new ApiPost("post/comment/new", params);
		Thread t = new Thread(ap);
		t.start();
		while (!ap.isComplete) {
			Log.d("Thread API: ", "Đang đăng bình luận...");
		}

		Log.d("Response", ap.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ap.response);
			if (!obj.has("error")) {
				return new PostComment(comment,this.post_info);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<PostComment> GetComments()
	{
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("conf", this.group.id);

		ApiGet ag = new ApiGet("confession/id", params);
		Thread t = new Thread(ag);
		t.start();
		while (!ag.isComplete) {
			Log.d("Thread API: ", "Đang lấy tất cả bình luận...");
		}

		ArrayList<PostComment> postComments = new ArrayList<PostComment>();
		Log.d("Response", ag.response);
		JSONObject obj = null;
		try {
			obj = new JSONObject(ag.response);
			if (!obj.has("error")) {
				JSONArray items = obj.getJSONArray("posts");

				for (int i = 0; i < items.length(); i++) {
					JSONObject item = items.getJSONObject(i);
					String postid = item.getString("_id");
					if(postid.equals(this.post_info.id))
					{
						JSONArray comments = item.getJSONArray("comments");
						for(int j=0;j<comments.length();j++)
						{
							JSONObject comment = comments.getJSONObject(j);

							String comment_id = comment.getString("_id");
							String content = comment.getString("content");

							PostCommentInfo postCommentInfo = new PostCommentInfo(comment_id,new BasicUserInfo("","","",""),content);
;							PostComment comment_info = new PostComment(postCommentInfo,this.post_info);
							postComments.add(comment_info);
						}
					}
				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return postComments;
	}
}
