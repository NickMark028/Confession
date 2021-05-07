package com.example.confession.binders.post;

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

		if (content.isEmpty())
		{
			view.OnAddCommentFailure("Content can't be empty");
			return;
		}

		User user = User.GetInstance();
		GroupPost post = new GroupPost(post_info);
		PostComment comment = post.AddComment();

		if (post != null)
			view.AddPostSuccess(post);
		else
			view.AddPostFailure("Failed to add post");
	}
	}
}
