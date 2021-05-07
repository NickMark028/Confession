package com.example.confession.binders.group;

import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface GetAdminsBinder {

    interface View {

        void OnGetAdminsSuccess(ArrayList<BasicUserInfo> admins);
        void OnGetAdminsFailure(String error);
    }

    interface Presenter {

        void HandleGetAdmins(ConfessionGroupInfo group_info);
    }
}
