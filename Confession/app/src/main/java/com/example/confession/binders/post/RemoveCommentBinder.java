package com.example.confession.binders.post;

import com.example.confession.models.data.PostCommentInfo;

public interface RemoveCommentBinder {

	interface View {

		void OnRemoveCommentSuccess();
		void OnRemoveCommentFailure(String error);
	}

	interface Presenter {

		void HandleRemoveComment(PostCommentInfo comment_info);
	}
}
