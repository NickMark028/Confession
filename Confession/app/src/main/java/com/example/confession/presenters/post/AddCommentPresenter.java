package com.example.confession.presenters.post;

import com.example.confession.binders.post.AddCommentBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;

public class AddCommentPresenter implements AddCommentBinder.Presenter {

	private final AddCommentBinder.View view;

	public AddCommentPresenter(AddCommentBinder.View view) {
		this.view = view;
	}

	@Override
	public void HandleAddComment(GroupPostInfo post_info, String content) {

		if (content.isEmpty()) {
			view.OnAddCommentFailure("Content can't be empty");
			return;
		}

		User user = User.GetInstance();
		GroupPost post = new GroupPost(post_info);
		PostCommentInfo comment_info = new PostCommentInfo(post_info, user.GetBasicUserInfo(), content);
		PostComment comment = post.AddComment(comment_info);

		if (post != null)
			view.OnAddCommentSuccess(comment);
		else
			view.OnAddCommentFailure("Failed to add comment");
	}
}
