package com.example.confession.presenters.group_done;

import com.example.confession.binders.comment_done.RejectPostBinder;

public class RejectPostPresenter implements RejectPostBinder.Presenter {
    private  final RejectPostBinder.View view;

    public RejectPostPresenter(RejectPostBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandleRejectPost() {
        if(true){
            view.OnRejectPostSuccess();
        }
        else {
            view.OnRejectPostFailure("Failed to Reject Post");
        }
    }
}
