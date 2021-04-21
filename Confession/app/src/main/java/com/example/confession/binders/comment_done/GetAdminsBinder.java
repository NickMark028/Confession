package com.example.confession.binders.comment_done;

import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface GetAdminsBinder {

    interface View {

        void OnGetAdminsSuccess(ArrayList<BasicUserInfo> groups);
        void OnGetAdminsFailure(String error);
    }

    interface Presenter {

        void HandleGetAdmins(ConfessionGroupInfo group_info);
    }
}
