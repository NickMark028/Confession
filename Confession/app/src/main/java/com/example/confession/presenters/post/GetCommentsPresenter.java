package com.example.confession.presenters.post;

import com.example.confession.binders.post.GetCommentsBinder;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public class GetCommentsPresenter implements GetCommentsBinder.Presenter {

	private final GetCommentsBinder.View view;

	public GetCommentsPresenter(GetCommentsBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleGetComments(GroupPostInfo info) {

		GroupPost post = new GroupPost(info);
		ArrayList<PostComment> comments = post.GetComment();

		if (comments != null)
			view.OnGetCommentsSuccess(comments);
		else
			view.OnGetCommentsFailure("Failed to get comments");
	}
}
