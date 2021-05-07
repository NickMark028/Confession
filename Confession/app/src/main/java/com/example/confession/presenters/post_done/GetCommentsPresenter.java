package com.example.confession.presenters.post_done;

import com.example.confession.binders.post.GetCommentsBinder;
import com.example.confession.models.data.GroupPostInfo;

public class GetCommentsPresenter implements GetCommentsBinder.Presenter {

	private final GetCommentsBinder.View view;

	public GetCommentsPresenter(GetCommentsBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleGetComments(GroupPostInfo info) {

//		GroupPost post = new GroupPost(info, );
//
//		if (group != null)
//			view.OnCreateGroupSuccess(group);
//		else
//			view.OnCreateGroupFailure("Duplicate short name");
	}
}
