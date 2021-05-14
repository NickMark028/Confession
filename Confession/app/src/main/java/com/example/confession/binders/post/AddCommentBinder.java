package com.example.confession.binders.post;

import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.GroupPostInfo;

public interface AddCommentBinder {

	interface View {

		void OnAddCommentSuccess(PostComment comment);
		void OnAddCommentFailure(String error);
	}


}
