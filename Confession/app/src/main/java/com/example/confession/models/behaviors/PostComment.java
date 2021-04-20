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

	public PostComment AddComment(PostCommentInfo comment, String auth_token)
	{

		return null;
	}

	public ArrayList<PostCommentInfo> GetComments(String auth_token)
	{
		return null;
	}

	public boolean RemoveComment(String comment_id, String from_user_id, String auth_token)
	{
		return false;
	}

	public boolean React(String user_id, String auth_token)
	{
		return false;
	}

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
