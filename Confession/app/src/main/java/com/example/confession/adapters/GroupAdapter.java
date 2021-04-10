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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GroupAdapter extends BaseAdapter {

	Context context;
	ArrayList<ConfessionGroup> groups;
	ArrayList<ConfessionGroupInfo> group;

//	public GroupAdapter(Context context, ArrayList<ConfessionGroup> groups) {
//
//		this.context = context;
//		this.groups = groups;
//	}

	//for testing GUI only
	public GroupAdapter(Context context, ArrayList<ConfessionGroupInfo> group) {

		this.context = context;
		this.group = group;
	}


	@Override
	public int getCount() {
		return group.size();
	}

	@Override
	public Object getItem(int i) {
		return group.get(i);
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
		ImageView search_gr_avatar = row.findViewById(R.id.search_gr_avatar);
		TextView search_gr_name = row.findViewById(R.id.search_gr_name);
		TextView search_gr_member = row.findViewById(R.id.search_gr_member);
		TextView search_gr_user_state = row.findViewById(R.id.search_gr_user_state);

		// Init data
		//ConfessionGroup group = groups.get(i);
//		iv_avatar.setBackgroundResource(...);
		//search_gr_name.setText(group.GetGroupInfo().name);
		//search_gr_member.setText(group.GetMemberCount());


		//Testing GUI
		search_gr_name.setText(group.get(i).name);
		search_gr_member.setText("101");

		if (checkIsUserInGroup()) {
			search_gr_user_state.setText("Joined");
		} else {
			search_gr_user_state.setText("");
		}


		return row;
	}

	//Kiem tra User co trong Group khong
	private boolean checkIsUserInGroup(){
		return true;
	}
}
