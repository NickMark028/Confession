package com.example.confession.binders.post;

import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public interface GetUserCommentsBinder {

	interface View {

		void OnGetUserCommentsSuccess(ArrayList<PostComment> comments);
		void OnGetUserCommentsFailure(String error);
	}


}
