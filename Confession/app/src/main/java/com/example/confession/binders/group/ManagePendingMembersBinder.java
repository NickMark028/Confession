package com.example.confession.binders.group;

import androidx.recyclerview.widget.RecyclerView;

import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;

public interface ManagePendingMembersBinder {

	interface View {

		void OnAcceptPendingMembersSuccess(RecyclerView.ViewHolder view_holder);
		void OnAcceptPendingMembersFailure(RecyclerView.ViewHolder view_holder, String error);

		void OnRejectPendingMembersSuccess(RecyclerView.ViewHolder view_holder);
		void OnRejectPendingMembersFailure(RecyclerView.ViewHolder view_holder, String error);
	}

	interface Presenter {

		void HandleAcceptPendingMembers(BasicUserInfo user_info, ConfessionGroupInfo group_info, RecyclerView.ViewHolder view_holder);
		void HandleRejectPendingMembers(BasicUserInfo user_info, ConfessionGroupInfo group_info, RecyclerView.ViewHolder view_holder);
	}
}
