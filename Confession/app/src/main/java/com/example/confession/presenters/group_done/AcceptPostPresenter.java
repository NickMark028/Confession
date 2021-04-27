package com.example.confession.presenters.group_done;

import com.example.confession.binders.comment_done.AcceptPostBinder;

public class AcceptPostPresenter implements AcceptPostBinder.Presenter {
    private final AcceptPostBinder.View view;

    public AcceptPostPresenter(AcceptPostBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandleAcceptPost() {
        if(true){
            view.OnAcceptPostSuccess();
        }
        else {
            view.OnAcceptPostFailure("Failed to Accept Post");
        }
    }
}
