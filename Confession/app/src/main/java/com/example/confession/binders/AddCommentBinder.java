package com.example.confession.binders;

import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.data.BasicUserInfo;

public interface AddCommentBinder {

	interface View {

		void OnAddCommentSuccess();
		void OnAddCommentFailure(String error);
	}

	interface Presenter {

		void HandleAddComment(GroupPost post, String content);
	}
}
