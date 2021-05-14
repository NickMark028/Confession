package com.example.confession.presenters.post;

import com.example.confession.binders.post.GetCommentsBinder;
import com.example.confession.binders.post.GetUserCommentsBinder;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;

import java.util.ArrayList;

public class GetUserCommentsPresenter implements GetUserCommentsBinder.Presenter {

	private final GetUserCommentsBinder.View view;

	public GetUserCommentsPresenter(GetUserCommentsBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleGetUserComments(GroupPostInfo info) {

		GroupPost post = new GroupPost(info);
		ArrayList<PostCommentInfo> comments = post.GetComments(User.GetAuthToken());

		if (comments != null)
			view.OnGetUserCommentsSuccess(comments);
		else
			view.OnGetUserCommentsFailure("Failed to get comments");
	}
}
