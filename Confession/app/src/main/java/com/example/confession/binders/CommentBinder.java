package com.example.confession.binders;

import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.PostComment;

import java.util.ArrayList;

public interface CommentBinder {

	interface View {

		void OnPostCommentSuccess(int code);
		void OnPostCommentFailure(int error_code);
	}

	interface Presenter {

		void HandlePostComment(PostComment pc);
	}
}
