package com.example.confession.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.confession.R;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class GroupAdapter extends BaseAdapter {

	Context context;
	ArrayList<ConfessionGroup> groups;

	public GroupAdapter(Context context, ArrayList<ConfessionGroup> groups) {

		this.context = context;
		this.groups = groups;
	}

	@Override
	public int getCount() {
		return groups.size();
	}

	@Override
	public Object getItem(int i) {
		return groups.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		View row = inflater.inflate(R.layout.layout_group_info, null);

		//  Init view
		ImageView iv_avatar = row.findViewById(R.id.iv_avatar);
		TextView txt_group_name = row.findViewById(R.id.txt_group_name);
		TextView txt_member_count = row.findViewById(R.id.txt_member_count);
		Button btn_join = row.findViewById(R.id.btn_join);

		// Init data
		ConfessionGroup group = groups.get(i);
//		iv_avatar.setBackgroundResource(...);
		txt_group_name.setText(group.GetGroupInfo().name);
		txt_member_count.setText(group.GetMemberCount());

		// Init listener
		btn_join.setOnClickListener(view1 -> {
			//...
		});

		return row;
	}
}
