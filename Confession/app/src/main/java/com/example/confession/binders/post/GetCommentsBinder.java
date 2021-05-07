package com.example.confession.binders.post;

import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public interface GetCommentsBinder {

	interface View {

		void OnGetCommentsSuccess(ArrayList<PostComment> comments);
		void OnGetCommentsFailure(String error);
	}

	interface Presenter {

		void HandleGetComments(GroupPostInfo info);
	}
}
