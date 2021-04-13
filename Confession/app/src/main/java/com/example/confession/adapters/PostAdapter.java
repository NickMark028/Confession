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
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.data.GroupPostInfo;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {

	Context context;
	ArrayList<GroupPost> posts;

	public PostAdapter(Context context, ArrayList<GroupPost> posts) {

		this.context = context;
		this.posts = posts;
	}

	@Override
	public int getCount() {
		return posts.size();
	}

	@Override
	public Object getItem(int i) {
		return posts.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		View row = inflater.inflate(R.layout.layout_post, null);

		//  Init view
		TextView txt_group_name = row.findViewById(R.id.txt_group_name);
		TextView txt_time = row.findViewById(R.id.txt_time);
		TextView txt_content = row.findViewById(R.id.txt_content);
		ImageView iv_content = row.findViewById(R.id.iv_content);
		ImageView iv_react = row.findViewById(R.id.iv_react);

		// Init data
		GroupPost post = posts.get(i);
		GroupPostInfo post_info = post.GetPostInfo();
		txt_group_name.setText(post.GetGroup().name);
		txt_time.setText(post_info.time_created.toString());
		txt_content.setText(post_info.content.toString());
		// TODO background here + react
//		iv_content.setBackgroundResource(post_info.time_created.toString());

		txt_content.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				// ..

			}
		});

		// Init listener
		iv_react.setOnClickListener(view1 -> {
			//...
		});

		return row;
	}
}
