package com.example.confession.adapters.user;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confession.R;
import com.example.confession.binders.group.ManagePendingMembersBinder;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.group.ManagePendingMembersPresenter;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class GroupPendingMembersAdapter extends RecyclerView.Adapter<GroupPendingMembersAdapter.ViewHolder> {

	Context context;
	ArrayList<BasicUserInfo> groupPendingUser;
	ConfessionGroupInfo group_info;
	ManagePendingMembersBinder.Presenter presenter;
	int removePosition = -1;

	public GroupPendingMembersAdapter(Context context, ArrayList<BasicUserInfo> groupPending, ConfessionGroupInfo group_info) {
		this.context = context;
		this.groupPendingUser = groupPending;
		this.group_info = group_info;

	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.layout_pending_members, parent, false);
		return new GroupPendingMembersAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.InitData(position);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public int getItemCount() {
		return groupPendingUser.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements ManagePendingMembersBinder.View {
		private LinearLayout ll_group_pending_members;
		private ImageView ava_user_pending, accept_pending_member, reject_pending_members;
		private TextView pending_member_name, txt_peding_status;
		private MaterialCardView mcv_pending_status;
		private ProgressBar pending_mem_checking;
		private boolean checkPendingAcceptClick = false;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);

			presenter = new ManagePendingMembersPresenter(this);

			InitView(itemView);
			InitListener();
		}

		public void InitView(View view) {
			ll_group_pending_members = view.findViewById(R.id.ll_group_pending_members);
			ava_user_pending = view.findViewById(R.id.ava_user_pending);
			accept_pending_member = view.findViewById(R.id.accept_pending_member);
			reject_pending_members = view.findViewById(R.id.reject_pending_members);
			pending_member_name = view.findViewById(R.id.pending_member_name);

			txt_peding_status = view.findViewById(R.id.txt_peding_status);
			mcv_pending_status = view.findViewById(R.id.mcv_pending_status);
			pending_mem_checking = view.findViewById(R.id.pending_mem_checking);
		}

		public void InitData(int i) {
			BasicUserInfo user_info = groupPendingUser.get(i);
			//ava_user_pending.setImageResource((Integer) user_info.avatar);
			pending_member_name.setText(user_info.name);
			//check and reject chua lam
		}

		public void InitListener() {
			accept_pending_member.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							presenter.HandleAcceptPendingMembers(
									groupPendingUser.get(getLayoutPosition()),
									group_info
							);
						}
					}).start();
					checkPendingAcceptClick = true;


					accept_pending_member.setVisibility(View.GONE);
					reject_pending_members.setVisibility(View.GONE);
					pending_mem_checking.setVisibility(View.VISIBLE);

					removePosition = getLayoutPosition();
				}
			});
			reject_pending_members.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});

		}

		@Override
		public void OnAcceptPendingMembersSuccess() {

			((Activity) context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(context, "Accept Successfully", Toast.LENGTH_SHORT).show();
//                groupPendingUser.remove(removePosition);
//                groupPendingUser.notifyAll();
				}
			});
		}

		@Override
		public void OnAcceptPendingMembersFailure(String error) {
			((Activity) context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					removePosition = -1;
					Toast.makeText(context, "Failed to accpect", Toast.LENGTH_SHORT).show();
				}
			});
		}

		@Override
		public void OnRejectPendingMembersSuccess() {

		}

		@Override
		public void OnRejectPendingMembersFailure(ManagePendingMembersBinder.View view, String error) {

		}
	}
}
