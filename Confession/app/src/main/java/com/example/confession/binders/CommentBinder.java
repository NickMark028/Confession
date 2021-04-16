package com.example.confession.binders;

import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.PostComment;

import java.util.ArrayList;

public interface CommentBinder {

	interface View {

		void OnPostCommentSuccess();
		void OnPostCommentFailure(int error_code);
	}

	interface Presenter {

		void HandlePostComment(String content);
	}
}
