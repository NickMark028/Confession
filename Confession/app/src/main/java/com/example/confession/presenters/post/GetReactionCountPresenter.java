package com.example.confession.presenters.post;

import com.example.confession.binders.post.GetReactionCountBinder;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.GroupPostInfo;

public class GetReactionCountPresenter implements GetReactionCountBinder.Presenter {
    private  final GetReactionCountBinder.View view;

    public GetReactionCountPresenter(GetReactionCountBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandleGetReactionCount(GroupPostInfo postInfo) {
        GroupPost post = new GroupPost(postInfo);
        boolean check = post.React(User.GetInstance().GetID(),User.GetAuthToken());
        if(check != true)
        {
            view.OnGetCommentsSuccess(check);
        }
        else {
            view.OnGetCommentsFailure("Failed to get reaction");
        }
    }
}
