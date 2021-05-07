package com.example.confession.presenters.post_done;

import com.example.confession.binders.PostComent_done.AddCommentBinder;
import com.example.confession.binders.post.GetCommentsBinder;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;

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

	public static class AddCommentPresenter implements AddCommentBinder.Presenter {
	    private final AddCommentBinder.View view;

	    public AddCommentPresenter(AddCommentBinder.View view) {
	        this.view = view;
	    }


	    @Override
	    public void HandleAddComment(PostCommentInfo comment_info, GroupPostInfo post) {

	//        PostComment cmt = new PostComment(comment_info,post);
	//        PostComment comments = cmt.AddComment(comment_info, User.GetAuthToken());
	//        if(comments != null){
	//            view.OnAddCommentSuccess(comments);
	//        }
	//        else {
	//        view.OnAddCommentFailure("Failed to Add Comment!");
	    }}
}
