package com.example.confession.binders.post;

import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;

import java.util.ArrayList;

public interface GetCommentsBinder {

	interface View {

		void OnGetCommentsSuccess(ArrayList<PostCommentInfo> comments);
		void OnGetCommentsFailure(String error);
	}


}
