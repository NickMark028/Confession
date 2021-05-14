package com.example.confession.adapters.group;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.confession.R;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.ArrayList;

public class GroupListAdapter extends BaseAdapter {

	Context context;
	ArrayList<ConfessionGroupInfo> groups;

	private ImageView iv_group_item_gr_avatar;
	private TextView txt_group_list_gr_name;

	public GroupListAdapter(Context context, ArrayList<ConfessionGroupInfo> groups) {

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
		@SuppressLint("ViewHolder")
		View row = inflater.inflate(R.layout.layout_user_group_info, null);

		//  Init view
		InitView(row);

		// Init data
		InitData(i);

		return row;
	}

	public void ChangeData(ArrayList<ConfessionGroupInfo> data_groups){
		groups.clear();
		groups.addAll(data_groups);
		notifyDataSetChanged();
	}

	public void InitView(View row){
		iv_group_item_gr_avatar = row.findViewById(R.id.iv_group_item_gr_avatar);
		txt_group_list_gr_name = row.findViewById(R.id.txt_group_list_gr_name);
	}


	//Kiem tra User co trong Group khong
	private boolean checkIsUserInGroup(){
		return true;
	}
}
