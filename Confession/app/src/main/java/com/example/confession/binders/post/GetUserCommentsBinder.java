package com.example.confession.binders.post;

import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;

import java.util.ArrayList;

public interface GetUserCommentsBinder {

	interface View {

		void OnGetUserCommentsSuccess(ArrayList<PostCommentInfo> comments);
		void OnGetUserCommentsFailure(String error);
	}

	interface Presenter {

		void HandleGetUserComments(GroupPostInfo info);
	}
}
