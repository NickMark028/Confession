//package com.example.confession.models.behaviors;
//
//import android.os.Bundle;
//import android.util.Log;
//
//import com.example.confession.models.api.ApiGet;
//import com.example.confession.models.api.ApiPost;
//import com.example.confession.models.data.BasicUserInfo;
//import com.example.confession.models.data.ConfessionGroupInfo;
//import com.example.confession.models.data.GroupPostInfo;
//import com.example.confession.models.data.PostCommentInfo;
//import com.example.confession.models.data.UserInfo;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//// TODO Check lai cac method signature trong class diagram
//public class GroupPost {
//
//	protected final GroupPostInfo post_info;
//	protected ArrayList<PostComment> comments;
//
//	public GroupPost(GroupPostInfo post_info) {
//		this.post_info = post_info;
//	}
//
//	public String GetID() {
//		return post_info.id;
//	}
//
//	// DONE //
//	public boolean RemoveComment(PostCommentInfo comment, BasicUserInfo from, String auth_token) {
//
//		return false;
//	}
//
//	// DONE //
//	public boolean React(String user_id, String auth_token) {
//		// API Update
//		HashMap<String, String> temp_params = new HashMap<String, String>();
//		temp_params.put("token", auth_token);
//		temp_params.put("groupid", this.post_info.group.id);
////		Log.d("Token: ", auth_token);
////		Log.d("GroupID React: ", this.post_info.group.id);
//		ApiGet ag = new ApiGet("user/memberid/", temp_params);
//		ag.run();
//		Log.d("MemberID Res: ", ag.response);
//		String memberid = null;
//		try {
//			JSONObject temp_obj = new JSONObject(ag.response);
//			memberid = temp_obj.getString("memberid");
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("token", User.GetAuthToken());
//		params.put("memberid", memberid); // Sửa lại API
//		params.put("postid", this.post_info.id);
//
//		Log.d("Token: ", User.GetAuthToken());
//		Log.d("MemberID: ", memberid);
//		Log.d("PostID: ", this.post_info.id);
//
//		ApiPost ap = new ApiPost("post/reaction/react", params);
//		Log.d("Thread API: ", "Đang thả tim...");
//		ap.run();
//
//		Log.d("Response", ap.response);
//		JSONObject obj = null;
//		try {
//			obj = new JSONObject(ap.response);
//			if (!obj.has("error")) {
//				return true;
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	// DONE //
//	public ArrayList<PostComment> GetComment() {
//
//		return null;
//	}
//
//	// Done //
//	public int GetReactionCount(String auth_token) {
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("token", auth_token); // User.GetAuthToken()
//		params.put("postid", this.post_info.id);
//
//		ApiGet ag = new ApiGet("post/reactions", params);
//		Log.d("Thread API: ", "Đang lấy số lượng tim...");
//		ag.run();
//
//		Log.d("Response", ag.response);
//		JSONObject obj = null;
//		try {
//			JSONArray items = new JSONArray(ag.response);
//			if (!items.isNull(0)) {
//				return items.length();
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
//
//	// DONE //
//	public PostComment AddComment(PostCommentInfo comment, String auth_token) {
//		HashMap<String, String> temp_params = new HashMap<String, String>();
//		temp_params.put("token", auth_token);
//		temp_params.put("groupid", comment.post.group.id);
//		ApiGet ag = new ApiGet("user/memberid/", temp_params);
//		ag.run();
//		Log.d("MemberID Res: ", ag.response);
//		String memberid = null;
//		try {
//			JSONObject temp_obj = new JSONObject(ag.response);
//			memberid = temp_obj.getString("memberid");
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		Log.d("MemberID: ", memberid);
//
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("token", auth_token); //User.GetAuthToken()
//		params.put("memberid", memberid);
//		params.put("postid", comment.post.id);
//		params.put("content", comment.content);
//
//		ApiPost ap = new ApiPost("post/comment/new", params);
//		Log.d("Thread API: ", "Đang đăng bình luận...");
//		ap.run();
//
//		Log.d("Response", ap.response);
//		JSONObject obj = null;
//		try {
//			obj = new JSONObject(ap.response);
//			if (!obj.has("error")) {
//				return new PostComment(comment);
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//
//		return null;
//	}
//
//	// TODO sua lai tham so null thanh tham so thich hop
//	public ArrayList<PostCommentInfo> GetComments(String auth_token) {
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("conf", post_info.group.id);
//
//		ApiGet ag = new ApiGet("confession/id", params);
//		Log.d("Thread API: ", "Đang lấy tất cả bình luận...");
//		ag.run();
//
//		ArrayList<PostCommentInfo> postComments = new ArrayList<PostCommentInfo>();
//		Log.d("Response", ag.response);
//		JSONObject obj = null;
//		try {
//			obj = new JSONObject(ag.response);
//			if (!obj.has("error")) {
//				JSONArray items = obj.getJSONArray("posts");
//
//				for (int i = 0; i < items.length(); i++) {
//					JSONObject item = items.getJSONObject(i);
//					String postid = item.getString("_id");
//					if (postid.equals(this.post_info.id)) {
//						JSONArray comments = item.getJSONArray("comments");
//						for (int j = 0; j < comments.length(); j++) {
//							JSONObject comment = comments.getJSONObject(j);
//
//							String comment_id = comment.getString("_id");
//							String content = comment.getString("content");
//
//							// TODO sua lai tham so null thanh tham so thich hop
//							PostCommentInfo postCommentInfo = new PostCommentInfo(comment_id, post_info, new BasicUserInfo("", "", "", ""), content);
//
////							PostComment comment_info = new PostComment(postCommentInfo);
//							postComments.add(postCommentInfo);
//						}
//					}
//				}
//
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return postComments;
//	}
//
//	@Override
//	public String toString() {
//		return "GroupPost{" +
//				"post_info=" + post_info +
//				'}';
//	}
//}
