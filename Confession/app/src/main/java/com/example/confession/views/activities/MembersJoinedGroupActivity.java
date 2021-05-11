package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.confession.binders.group.GetMembersBinder;
import com.example.confession.binders.group.GetPendingMembersBinder;
import com.example.confession.models.data.BasicUserInfo;

import java.util.ArrayList;

public class MembersJoinedGroupActivity extends AppCompatActivity implements GetMembersBinder.View {
   private GetMembersBinder.Presenter presenterGetMembers;
   private MemberAda


    @Override
    public void OnGetMembersSuccess(ArrayList<BasicUserInfo> members) {

    }

    @Override
    public void OnGetMembersFailure(String error) {

    }
}
