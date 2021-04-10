package com.example.confession.models.behaviors;

import com.example.confession.models.api.ApiService;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;

public class PostComment {

	protected final PostCommentInfo comment_info;
	protected final GroupPostInfo post;

	public PostComment(PostCommentInfo comment_info, GroupPostInfo post) {
		this.comment_info = comment_info;
		this.post = post;
	}


}
