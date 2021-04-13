package com.example.confession.binders;

public interface CreateGroupBinder {
    interface View{
        void OnCreateGroupSuccess(int code);
        void OnCreateGroupFail(int error_code);
    }

    interface Presenter{
        void HandleCreateGroup(String group_name);
    }
}
