package com.example.confession.presenters;

import com.example.confession.binders.AddPostTabBinder;

public class AddPostPresenter implements AddPostTabBinder.Presenter {
    AddPostTabBinder.View view;

    public AddPostPresenter(AddPostTabBinder.View view){
        this.view = view;
    }


}
