package com.example.confession.binders;

import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.data.BasicUserInfo;

public interface CommentBinder {

	interface View {

		void OnPostCommentSuccess();
		void OnPostCommentFailure(String error);
	}

	interface Presenter {

		void HandlePostComment(GroupPost post, BasicUserInfo user, String content);
	}
}
