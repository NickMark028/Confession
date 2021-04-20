package com.example.confession.models.behaviors;

import android.os.Bundle;

import com.example.confession.models.api.ApiService;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;
import com.example.confession.models.data.UserInfo;

import java.util.ArrayList;

public class PostComment {

	protected final PostCommentInfo comment_info;
	protected final GroupPostInfo post;

	public PostComment(PostCommentInfo comment_info, GroupPostInfo post) {

		this.comment_info = comment_info;
		this.post = post;
	}

	public static PostComment From(Bundle bundle) {

		PostCommentInfo comment_info = (PostCommentInfo) bundle.getSerializable("comment_info");
		GroupPostInfo post = (GroupPostInfo) bundle.getSerializable("post");

		return new PostComment(comment_info, post);
	}

	public Bundle ToBundle() {

		Bundle bundle = new Bundle();
		bundle.putSerializable("comment_info", this.comment_info);
		bundle.putSerializable("post", this.post);
		return bundle;
	}

	public String GetID(){
		return comment_info.id;
	}

	// Không có làm chức năng trả lời bình luận //
	public PostComment AddComment(PostCommentInfo comment, String auth_token)
	{

		return null;
	}

	// Không có làm chức năng trả lời bình luận //
	public ArrayList<PostCommentInfo> GetComments(String auth_token)
	{
		return null;
	}

	// Write API later //
	public boolean RemoveComment(String comment_id, String from_user_id, String auth_token)
	{
		return false;
	}

	// Bình luận không có chức năng thả cảm xúc //
	public boolean React(String user_id, String auth_token)
	{
		return false;
	}

	// Bình luận không có chức năng thả cảm xúc //
	public int GetReactionCount(String auth_token)
	{
		return 0;
	}

	@Override
	public String toString() {
		return "PostComment{" +
				"comment_info=" + comment_info +
				'}';
	}
}
