package com.example.confession.presenters.group_done;

import com.example.confession.binders.comment_done.GetAdminsBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class GetAdminsPresenter implements GetAdminsBinder.Presenter {

    private final GetAdminsBinder.View view;

    public GetAdminsPresenter(GetAdminsBinder.View view) {
        this.view = view;
    }

    @Override
    public void HandleGetAdmins(ConfessionGroupInfo group_info) {

        ConfessionGroup group = new ConfessionGroup(group_info);
        ArrayList<BasicUserInfo> admins = group.GetAdmins(User.GetAuthToken());

        if (admins != null)
            view.OnGetAdminsSuccess(admins);
        else
            view.OnGetAdminsFailure("Failed to get admins");
    }
}
