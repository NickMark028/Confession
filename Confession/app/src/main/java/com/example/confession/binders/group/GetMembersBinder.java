package com.example.confession.binders.group;

import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public interface GetMembersBinder {

	interface View {

		void OnGetMembersSuccess(ArrayList<BasicUserInfo> members);
		void OnGetMembersFailure(String error);
	}


}
