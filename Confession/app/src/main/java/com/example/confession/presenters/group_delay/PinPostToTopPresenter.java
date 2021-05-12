package com.example.confession.presenters.group_delay;

import com.example.confession.binders.group_delay.PinPostToTopBinder;

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
