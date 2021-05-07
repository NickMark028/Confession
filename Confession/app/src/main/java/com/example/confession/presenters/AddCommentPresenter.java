package com.example.confession.presenters;

import com.example.confession.binders.AddCommentBinder;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;

public class AddCommentPresenter implements AddCommentBinder.Presenter {

	private final AddCommentBinder.View view;

	public AddCommentPresenter(AddCommentBinder.View view) {

		this.view = view;
	}

	@Override
	public void HandleAddComment(GroupPostInfo post_info, String content) {

		User user = User.GetInstance();
		PostCommentInfo comment_info = new PostCommentInfo(post_info, user.GetBasicUserInfo(), content);
		PostComment comment = new GroupPost(post_info, ).AddComment(comment_info, user.GetBasicUserInfo());

		if (comment != null)
			view.OnAddCommentSuccess();
		else
			view.OnAddCommentFailure("Failed to add comment");
	}
}
