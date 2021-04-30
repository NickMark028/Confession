package com.example.confession.presenters;

import com.example.confession.binders.AddCommentBinder;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.PostCommentInfo;

public class AddCommentPresenter implements AddCommentBinder.Presenter {

	private final AddCommentBinder.View view;

	public AddCommentPresenter(AddCommentBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleAddComment(GroupPost post, String content) {

		User user = User.GetInstance();
		PostCommentInfo info = new PostCommentInfo(user.GetBasicUserInfo(), content);
		PostComment comment = post.AddComment(info, user.GetBasicUserInfo());

		if (comment != null)
			view.OnAddCommentSuccess();
		else
			view.OnAddCommentFailure("Failed to add comment");
	}
}
