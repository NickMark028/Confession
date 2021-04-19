package com.example.confession.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.confession.R;
import com.example.confession.models.data.ConfessionGroupInfo;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GroupSearchAdapter extends BaseAdapter {

	Context context;
	ArrayList<ConfessionGroupInfo> groups;
	Set<String> joined_groups;

	private ImageView search_gr_avatar;
	private TextView search_gr_name, search_gr_member, search_gr_user_state;

	public GroupSearchAdapter(Context context, ArrayList<ConfessionGroupInfo> groups, Set<String> joined_groups) {

		this.context = context;
		this.groups = groups;
		this.joined_groups = joined_groups;
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
		InitView(row);

		// Init data
		InitData(i);

		//Data for testing GUI
//		search_gr_name.setText(group.get(i).name);
//		search_gr_member.setText("101");

		return row;
	}

	public void InitView(View row){

		search_gr_avatar = row.findViewById(R.id.search_gr_avatar);
		search_gr_name = row.findViewById(R.id.search_gr_name);
		search_gr_member = row.findViewById(R.id.search_gr_member);
		search_gr_user_state = row.findViewById(R.id.search_gr_user_state);
	}

	public void InitData(int pos){

		ConfessionGroupInfo cgi = groups.get(pos);

		//search_gr_avatar.setImageResource(cgi.avatar); int # String - not match
		search_gr_name.setText(cgi.name);
		search_gr_member.setText(Integer.toString(cgi.members));

		if (joined_groups.contains(cgi.id)) {
			search_gr_user_state.setText("Joined");
		} else {
			search_gr_user_state.setText("");
		}
	}
}
