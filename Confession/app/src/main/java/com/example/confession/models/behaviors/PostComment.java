package com.example.confession.models.behaviors;

import android.os.Bundle;

import com.example.confession.models.api.ApiService;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;
import com.example.confession.models.data.UserInfo;

import java.util.ArrayList;

public class PostComment {

	protected final PostCommentInfo comment_info;

	public PostComment(PostCommentInfo comment_info) {
		this.comment_info = comment_info;
	}

	public String GetID(){
		return comment_info.id;
	}
}
