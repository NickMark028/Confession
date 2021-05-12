package com.example.confession.presenters.group_delay;

import com.example.confession.binders.post.RemoveCommentBinder;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.PostCommentInfo;

public class RemovePostPresenter implements RemoveCommentBinder.Presenter {

    private final RemoveCommentBinder.View view;

    public RemovePostPresenter(RemoveCommentBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandleRemoveComment(PostCommentInfo comment_info) {

        GroupPost post = new GroupPost(comment_info.post);
        User user = User.GetInstance();
        BasicUserInfo user_info = user.GetBasicUserInfo();
        String token = User.GetAuthToken();

        if (post.RemoveComment(comment_info, user_info, token))
            view.OnRemoveCommentSuccess();
        else
            view.OnRemoveCommentFailure("Failed to remove comment");

    }
}
