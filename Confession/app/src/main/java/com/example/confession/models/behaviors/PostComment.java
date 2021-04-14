package com.example.confession.models.behaviors;

import android.os.Bundle;

import com.example.confession.models.api.ApiService;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;
import com.example.confession.models.data.UserInfo;

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

	@Override
	public String toString() {
		return "PostComment{" +
				"comment_info=" + comment_info +
				'}';
	}
}
