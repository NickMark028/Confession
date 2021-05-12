package com.example.confession.presenters.post;

import com.example.confession.binders.post.RemoveCommentBinder;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.PostCommentInfo;

public class RemoveCommentPresenter implements RemoveCommentBinder.Presenter {

	private final RemoveCommentBinder.View view;

	public RemoveCommentPresenter(RemoveCommentBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleRemoveComment(PostCommentInfo comment_info) {

		GroupPost post = new GroupPost(comment_info.post);

		User user = User.GetInstance();
		if (post.RemoveComment(comment_info, user.GetBasicUserInfo(), User.GetAuthToken()))
			view.OnRemoveCommentSuccess();
		else
			view.OnRemoveCommentFailure("Failed to remove comment");
	}
}
