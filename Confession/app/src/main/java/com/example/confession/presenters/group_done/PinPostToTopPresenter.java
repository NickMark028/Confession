package com.example.confession.presenters.group_done;

import com.example.confession.binders.comment_done.PinPostToTopBinder;

public class PinPostToTopPresenter implements PinPostToTopBinder.Presenter {
    private final PinPostToTopBinder.View view;

    public PinPostToTopPresenter(PinPostToTopBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandlePinPostToTop() {
        if (true){
            view.OnPinPostToTopSuccess();
        }
        else {
            view.OnPinPostToTopFailure("Failed to Pin To Top!");
        }
    }
}
