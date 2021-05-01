package com.example.confession.models.behaviors;

import android.os.Bundle;
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

	public static GroupPost From(Bundle bundle) {

		GroupPostInfo post_info = (GroupPostInfo) bundle.getSerializable("post_info");
		ConfessionGroupInfo group = (ConfessionGroupInfo) bundle.getSerializable("group");

		return new GroupPost(post_info, group);
	}

	public Bundle ToBundle() {

		Bundle bundle = new Bundle();
		bundle.putSerializable("post_info", this.post_info);
		bundle.putSerializable("group", this.group);
		return bundle;
	}

	public String GetID(){
		return post_info.id;
	}

	// Write API later //
	public boolean RemoveComment(PostComment comment, BasicUserInfo from) {
		return false;
	}



	// Sửa lại API tự lấy member id từ user id mới chạy được //
	public boolean React(BasicUserInfo user)
	{
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", User.GetAuthToken());
		params.put("memberid", "6059a449abdfae07a4e77646"); // Sửa lại API
		params.put("postid", this.post_info.id);

		ApiPost ap = new ApiPost("post/reaction/react", params);
		Log.d("Thread API: ", "Đang thả tim...");
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

	public PostComment[] GetComment()
	{

		return null;
	}

	// Done //
	public int GetReactionCount()
	{
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", User.GetAuthToken());
		params.put("postid", this.post_info.id);

		ApiGet ag = new ApiGet("post/reactions", params);
		Log.d("Thread API: ", "Đang lấy số lượng tim...");
		ag.run();

		Log.d("Response", ag.response);
		JSONObject obj = null;
		try {
			JSONArray items = new JSONArray(ag.response);
			if (!items.isNull(0))
			{
				return items.length();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public GroupPostInfo GetPostInfo() {
		return post_info;
	}

	public ConfessionGroupInfo GetGroup() {
		return group;
	}

	// Done //
	public PostComment AddComment(PostCommentInfo comment, BasicUserInfo member)
	{
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", User.GetAuthToken());
		params.put("memberid", member.id);
		params.put("postid", comment.id);
		params.put("content", comment.content);

		ApiPost ap = new ApiPost("post/comment/new", params);
		Log.d("Thread API: ", "Đang đăng bình luận...");
		ap.run();

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
							PostComment comment_info = new PostComment(postCommentInfo,this.post_info);
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

	@Override
	public String toString() {
		return "GroupPost{" +
				"post_info=" + post_info +
				'}';
	}
}
